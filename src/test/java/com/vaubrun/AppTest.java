package com.vaubrun;

import com.vaubrun.exception.BadApplicationParameterException;
import com.vaubrun.exception.MissingParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

@DisplayName("Parameters validation scenarios")
class AppTest {

    @Test
    @DisplayName("Throw exception when no program parameters")
    public void mainTrowExceptionOnMissingParameters() {
        //Given
        String[] args = new String[0];
        //Then
        Assertions.assertThrows(MissingParameterException.class, () -> {
            App.main(args);
        });
    }

    @Test
    @DisplayName("Throw exception when less than 2 parameters are passed")
    public void mainTrowExceptionOnMissingParameter() {
        //Given
        String[] args = new String[1];
        args[0] = "dummy";
        //Then
        Assertions.assertThrows(MissingParameterException.class, () -> {
            App.main(args);
        });
    }

    @Test
    @DisplayName("Throw exception input file is incorrect")
    public void shouldValidateInputFileExists() {
        //Given
        String inputFilePath = "path/that/does/not/exists";
        String[] args = new String[2];
        args[0] = inputFilePath;
        args[1] = "dummy";
        //Then
        Assertions.assertThrows(BadApplicationParameterException.class, () -> {
            App.main(args);
        });
    }

    @Test
    @DisplayName("Throw exception when output file already exists")
    public void shouldValidateOutputFileDoentExists() {
        //Given
        String inputFilePath = Paths.get("src", "test", "resources", "simple_map.txt").toAbsolutePath().toString();
        String[] args = new String[2];
        args[0] = inputFilePath;
        args[1] = inputFilePath;
        //Then
        Assertions.assertThrows(BadApplicationParameterException.class, () -> {
            App.main(args);
        });
    }
}