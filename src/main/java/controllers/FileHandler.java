package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import models.Sutra;

public class FileHandler {

	public String readFile(String path) {

		try (FileInputStream fis = new FileInputStream(path);
				InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(isr)) {

			StringBuilder context = new StringBuilder();
			String temp;
			while ((temp = reader.readLine()) != null) {
				context.append(temp + " ");
				context.append("\n"); // this could be causing problems
			}
			//remove last char from 
			String result = context.toString();
			return result.trim();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void writeFile(String context) {

		String path = "Cleaned\\temp.txt";

		try (FileOutputStream fos = new FileOutputStream(path);
				OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				BufferedWriter writer = new BufferedWriter(osw)) {

			writer.write(context);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] getFilesInFolder(String folderPath) {
		String[] paths = null;
		try (Stream<Path> p = Files.walk(Paths.get(folderPath))) {
			paths = p.filter(Files::isRegularFile).map(Path::toString).toArray(String[]::new);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return paths;
	}
}
