package com.ex.util.file;

public class Filepath {

    public String path;    // 路径   "path/path2"
    public String name;    // 文件名 "filename"
    public String suffix;  // 后缀   "jpg"
    public Boolean isLegal;

    public Filepath(String filename) {
        if (filename != null && filename.length() > 1) {
//            if (filename.startsWith("/")) {
//                filename = filename.substring(1);
//            }
            int index = filename.lastIndexOf(".");
            if (index > 0 && index + 1 != filename.length()) {
                this.suffix = filename.substring(index + 1);        // 取得后缀
                String pre = filename.substring(0, index);
                if (pre.length() > 0) {
                    int i = pre.lastIndexOf("/");
                    if (i < 0) {
                        this.path = "";
                        this.name = pre;
                        this.isLegal = true;
                        return;
                    } else if (i + 1 != pre.length()) {
                        this.path = pre.substring(0, i);
                        this.name = pre.substring(i + 1);
                        this.isLegal = true;
                        return;
                    }
                }
            }
        }
        this.isLegal = false;
    }

    public String buildFilename() {
        if (isLegal) {
            if (path == null || path.length() == 0) {
                return name + "." + suffix;
            }
            return path + "/" + name + "." + suffix;
        }
        return "";
    }


}
