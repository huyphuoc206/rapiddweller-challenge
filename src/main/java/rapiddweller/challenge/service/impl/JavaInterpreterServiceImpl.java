package rapiddweller.challenge.service.impl;

import org.springframework.stereotype.Service;
import rapiddweller.challenge.payload.InterpretCodeRequest;
import rapiddweller.challenge.payload.InterpretContextRequest;
import rapiddweller.challenge.service.InterpreterService;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class JavaInterpreterServiceImpl implements InterpreterService {
    private final String EXECUTE_FILE_NAME = "Temp.java";
    private StringBuilder context = new StringBuilder();

    @Override
    public String interpret(InterpretCodeRequest payload) {
        try {
            saveCodeToFile(payload.getInput(), EXECUTE_FILE_NAME);
            return compileCode(EXECUTE_FILE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalCallerException("Failed");
        }
    }

    @Override
    public void addCodeToInterpreterContext(InterpretContextRequest payload) {
        if (payload.isClear()) {
            context.setLength(0);
            return;
        }
        if (payload.getInput() != null && !payload.getInput().trim().isEmpty()) {
            context.append("\n").append("static ").append(payload.getInput().trim());
        }
    }

    private void saveCodeToFile(String code, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            StringBuilder sb = new StringBuilder();
            sb.append("import java.util.*;\n");
            sb.append("public class ").append(fileName.replace(".java", "")).append("{\n");
            sb.append(context);
            sb.append("public static void main(String[] args) {\n");
            sb.append(code);
            sb.append("}\n}");
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String compileCode(String fileName) throws IOException, InterruptedException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // Compile java file to .class file
        int compilationResult = compiler.run(null, null, null, fileName);

        if (compilationResult == 0) {
            // Run .class file
            Process process = Runtime.getRuntime().exec("java " + fileName.replace(".java", ""));

            // Capture and collect the output
            String output = printProcessOutput(process);

            // Wait for the process to complete
            process.waitFor();

            process.exitValue();
            return output;
        }
        throw new IllegalStateException("Error");
    }

    private String printProcessOutput(Process process) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }
}
