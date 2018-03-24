package solution.utils.logging.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import solution.utils.logging.Event;
import solution.utils.logging.EventLogger;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class FileEventLogger implements EventLogger {

    private String filename;
    private File file;

    @Autowired
    public FileEventLogger(
            @Value("${log_output_file}") String filename
    ) {
        this.filename = filename;
    }

    @PostConstruct
    protected void init() throws IOException {
        this.file = new File(filename);

        if (!file.exists()){
            boolean newFile = file.createNewFile();
            logMessage("File created = " + newFile);
        }
        boolean canWrite = file.canWrite();
        if (!canWrite) {
            throw new IOException();
        }
    }

    public void logEvent(Event event) {
        append(event.toString());
    }

    public void logMessage(String message) {
        append(message);
    }

    private void append(String text) {
        try {
            FileUtils.writeStringToFile(file, text + "\n", Charset.defaultCharset(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
