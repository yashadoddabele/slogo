package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.file.HistoryLoader;
import slogo.model.file.HistoryWriter;
import util.DukeApplicationTest;

public class FileTest extends DukeApplicationTest {

  private HistoryLoader historyLoader;
  private HistoryWriter historyWriter;
  private final String testWriterPath = "src/main/resources/slogo/programs/TestWriter.slogo";
  private final String testLoaderPath = "src/main/resources/slogo/programs/ex1.slogo";

  @BeforeEach
  void createFileWriter() {
    historyWriter = new HistoryWriter(testWriterPath);
    historyLoader = new HistoryLoader(testLoaderPath);
  }

  @Test
  void testLoader() {
    CommandHistory commandHistory = historyLoader.readHistoryFromFile();
    assertEquals(6, commandHistory.size());
  }

  @Test
  void testWriter() {
    CommandHistory commandHistory = new CommandHistory();
    commandHistory.addCommand("fd 10");
    commandHistory.addCommand("rt 90");
    historyWriter.writeHistoryToFile(commandHistory);
    try (BufferedReader reader = new BufferedReader(new FileReader(testWriterPath))) {
      assertTrue(reader.readLine().contains("fd 10"));
      assertTrue(reader.readLine().contains("rt 90"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
