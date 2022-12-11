package unisa.diem.seproject;

import java.io.File;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import unisa.diem.seproject.model.*;

public class LoadAndSaveTest {
    private Project sample;

    @BeforeEach
    public void initProject() {
        CommandManager cm = new CommandManager();
        sample = new Project(cm);
        Sheet sheet = new Sheet(cm);
        sample.addSheet(sheet);
    }

    @Test
    @DisplayName("Test save and load")
    public void testLoadAndSave() {
        Project.save(sample, new File("sample.proj"));
        Project saved = Project.load(new File("sample.proj"));
        assertNotNull(saved);
    }
}
