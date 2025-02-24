import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress play1 = new GameProgress(80, 1, 2, 150);
        GameProgress play2 = new GameProgress(100, 2, 4, 800);
        GameProgress play3 = new GameProgress(95, 1, 9, 1599);

        File savegames = new File("C://Users//Александра//Desktop//Games//savegames");
        List<String> saveGamesList = new ArrayList<>();

        saveGame("C://Users//Александра//Desktop//Games//savegames//save1.dat", play1);
        saveGame("C://Users//Александра//Desktop//Games//savegames//save2.dat", play2);
        saveGame("C://Users//Александра//Desktop//Games//savegames//save3.dat", play3);

        for (File file : savegames.listFiles()) {
            saveGamesList.add(file.getAbsolutePath());
        }

        zipFiles("C://Users//Александра//Desktop//Games//savegames//zip.zip", saveGamesList); // ИМЯ ПАПКИ
    }


    public static void saveGame(String path, GameProgress play) {
        try (FileOutputStream sg = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(sg)) {
            oos.writeObject(play);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipPath, List<String> list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    FileInputStream fis = new FileInputStream(list.get(i));
                    ZipEntry entry = new ZipEntry(list.get(i));
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}