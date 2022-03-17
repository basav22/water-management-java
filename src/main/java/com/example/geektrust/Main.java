package com.example.geektrust;

import com.example.geektrust.usecase.UseCase;
import com.example.geektrust.usecase.UseCaseImpl;
import com.example.geektrust.util.InputDataParser;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        var waterInLitrePerPerson = 10;

        UseCase useCase = new UseCaseImpl(waterInLitrePerPerson);
        InputDataParser inputDataParser = new InputDataParser(useCase);

        var inputFilePath = Paths.get(args[0]);
        var commands = inputDataParser.parseInputFromFile(inputFilePath);

        commands.forEach(Runnable::run);
	}
}
