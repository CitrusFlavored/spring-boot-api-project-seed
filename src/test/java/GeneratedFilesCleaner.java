import java.io.File;
import java.util.Scanner;

import static com.company.project.core.ProjectConstant.*;

/**
 * 清除代码生成器生成的文件。
 */
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

    /**
     * 格式化包名为文件夹路径
     * @param rawPathStr 包名
     * @return 包路径
     */
    public static String pathReplace(String rawPathStr) {
        return rawPathStr.replace(".", File.separator);
    }

    /**
     * 删除文件夹
     * @param folder 文件夹
     */
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

