package com.crow.util;


import com.crow.file.FileVo;
import com.crow.file.method.FileCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件工具类
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 判断文件是否存在
     *
     * @param dir 文件路径需要包含文件名
     * @return Boolean
     */
    public static Boolean isFileExist(String dir) {
        return new File(dir).exists();
    }

    /**
     * 复制文件 比 Files更快
     *
     * @param srcPath  源文件路径
     * @param destPath 目标文件路径
     * @return File
     */
    public static File copyFile(String srcPath, String destPath) {
        try (
                FileChannel src = new FileInputStream(srcPath).getChannel();
                FileChannel dest = new FileInputStream(destPath).getChannel()
        ) {
            dest.transferFrom(src, 0, src.size());
        } catch (IOException e) {
            logger.error("复制文件失败", e);
            return null;
        }
        return new File(destPath);
    }

    /**
     * 删除文件
     *
     * @param path     文件路径
     * @param filename 文件名
     * @return boolean
     */
    public static boolean deleteFile(String path, String filename) {
        return deleteFile(Paths.get(path, filename).toString());
    }

    /**
     * 删除文件
     *
     * @param path 文件路径
     * @return boolean
     * @throws IOException IOException
     */
    public static boolean deleteFile(String path) {
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            logger.error("删除文件失败", e);
            return false;
        }
        return true;
    }

    /**
     * 文件目录递归删除
     *
     * @param path 文件路径
     * @return boolean
     * @throws IOException IOException
     */
    public static boolean deleteDirectory(String path) throws IOException {
        FileCondition condition = file -> !file.toString().startsWith("C:") || !file.toString().startsWith("root");
        return deleteDirectory0(path, condition, condition, condition);
    }

    /**
     * 文件目录递归删除
     *
     * @param path      文件路径
     * @param visit     访问文件时触发该方法
     * @param preVisit  访问子目录前触发该方法
     * @param postVisit 访问目录之后触发该方法
     * @return boolean
     * @throws IOException IOException
     */
    public static boolean deleteDirectory(String path, FileCondition visit, FileCondition preVisit, FileCondition postVisit) throws IOException {
        return deleteDirectory0(path, visit, preVisit, postVisit);
    }

    /**
     * 递归删除
     *
     * @param path 文件路径
     * @return File
     */
    private static boolean deleteDirectory0(String path, FileCondition visit, FileCondition preVisit, FileCondition postVisit) throws IOException {
        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
                    // 在访问文件时触发该方法
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if (!visit.condition(file)) {
                            logger.info("文件被跳过: {}", file);
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        Files.delete(file);
                        logger.info("文件被删除: {}", file);
                        return FileVisitResult.CONTINUE;
                    }

                    // 在访问子目录前触发该方法
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        if (!preVisit.condition(dir)) {
                            logger.info("目录被跳过: {}", dir);
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        logger.info("目录被访问: {}", dir);
                        return FileVisitResult.CONTINUE;
                    }

                    // 在访问目录之后触发该方法
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        if (!postVisit.condition(dir)) {
                            logger.info("目录被跳过: {}", dir);
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        Files.delete(dir);
                        logger.info("目录被删除: {}", dir);
                        return FileVisitResult.CONTINUE;
                    }

                    // 在访问失败时触发该方法
                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        // 写一些具体的业务逻辑
                        return super.visitFileFailed(file, exc);
                    }

                }
        );
        return true;
    }

    public static String convertTimeToFile(String time){
        return time.replace(":", "_");
    }

    public static List<FileVo> listFiles(String dirPath, List<FileVo> fileVos){
        return listFiles(new File(dirPath), fileVos);
    }
    public static List<FileVo> listFiles(File folder, List<FileVo> fileVos) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        // 处理文件
                        FileVo fileVO;
                        if ((fileVO=processFile(file))!=null) {
                            fileVos.add(fileVO);
                        }
                    } else if (file.isDirectory()) {
                        // 递归处理子文件夹
                        listFiles(file, fileVos);
                    }
                }
            }
        }
        return fileVos;
    }

    public static FileVo processFile(File file) {
        if (file.isFile()) {
            String fileName = file.getName();
            long fileSize = file.length();
            String createTime = TimeUtil.getFormatDate(file.lastModified());
            return new FileVo(fileName, file.getPath(), createTime, fileSize);
        }
        return null;
    }

    public static void main(String[] args) {
        List<FileVo> fileVos = FileUtil.listFiles("./config/LiveRecord/", new ArrayList<>());
        System.out.println(fileVos.stream().filter(fileVo -> {
            return fileVo.getFileName().endsWith(".flv");
        }).collect(Collectors.toList()));
    }
}
