package com.vaubrun;

import com.vaubrun.exception.*;
import com.vaubrun.model.GameBoard;
import com.vaubrun.parser.GameBoardFactory;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.List;

@Log4j2
public class App {
    public static void main(String[] args) throws MissingParameterException, BadApplicationParameterException,
            IOException, MapCreationException, BadTreasureCountParameter, InvalidPositionException,
            BadPositionParameterException, InputFileFormatException {
        if (args.length < 2) {
            throw new MissingParameterException("Parameters for input and/or output files are missing, expecting 2 parameters");
        }
        //param 1 = path for input file
        File inputFile = new File(args[0]);
        if (!inputFile.exists()) {
            throw new BadApplicationParameterException(MessageFormat.format("Input file does not exists or path is invalid: {0}", inputFile.getAbsolutePath()));
        }
        //param 2 = path for output file
        File outputFile = new File(args[1]);
        if (outputFile.exists()) {
            throw new BadApplicationParameterException(MessageFormat.format("Output file already exists: {0}", outputFile.getAbsolutePath()));
        }
        List<String> descriptors = Files.readAllLines(inputFile.toPath(), Charset.defaultCharset());
        GameBoard gameBoard = GameBoardFactory.generateBoardFromFile(descriptors);

        gameBoard.makeAdventurerMove();

        List<String> outputs = gameBoard.generateOutput();
        Files.write(outputFile.toPath(), outputs, Charset.defaultCharset());

        log.info("Running");
    }
}
