package wzw.pikaqiu.model;

import java.util.List;

/**
 * Created by wenzi on 2016/5/19.
 */
public class Date {
    private String title;
    private String content;
    private int type;
    private List<ImageEntity> list;

    public List<ImageEntity> getList() {
        return list;
    }

    public void setList(List<ImageEntity> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class ImageEntity {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
