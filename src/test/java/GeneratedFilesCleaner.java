import java.io.File;
import java.util.Scanner;
import static com.company.project.core.ProjectConstant.*;

public class GeneratedFilesCleaner {
    private static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
    private static final String JAVA_PATH = "/src/main/java"; //java文件路径
    private static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径


    public static void main(String[] args) {

        // 目标文件夹
        File modelFolder = new File(PROJECT_PATH + JAVA_PATH , pathReplace(MODEL_PACKAGE)); //生成的Model所在包
        File mapperFolder = new File(PROJECT_PATH + JAVA_PATH , pathReplace(MAPPER_PACKAGE)); //生成的Mapper所在包
        File serviceFolder = new File(PROJECT_PATH + JAVA_PATH , pathReplace(SERVICE_PACKAGE)); //生成的Service所在包
        File controllerFolder = new File(PROJECT_PATH + JAVA_PATH , pathReplace(CONTROLLER_PACKAGE)); //生成的Controller所在包
        File mapperXMLFolder = new File(PROJECT_PATH + RESOURCES_PATH, pathReplace("mapper")); //生成的XML所在包

        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure you want to delete the files? Type 'yes|YES' to confirm:");
        String userInput = scanner.nextLine().toUpperCase();

        if (userInput.equals("YES")) {
            System.out.println("Deleting files...");
            // 执行删除文件的操作
            deleteFolder(modelFolder);
            deleteFolder(mapperFolder);
            deleteFolder(serviceFolder);
            deleteFolder(controllerFolder);
            deleteFolder(mapperXMLFolder);
            System.out.println("Files deleted successfully.");
        } else {
            System.out.println("Deletion canceled.");
        }

        scanner.close();
    }

    public static String pathReplace(String rawPathStr) {
        return rawPathStr.replace(".", File.separator);
    }

    public static void deleteFolder(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteFolder(file);
                }
            }
        }
        folder.delete();
        System.out.println("Deleted : " + folder.getAbsolutePath());
    }
}

