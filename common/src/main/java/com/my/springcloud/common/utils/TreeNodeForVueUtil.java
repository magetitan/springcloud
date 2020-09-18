package com.my.springcloud.common.utils;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 树节点工具类
 *
 */
@Data
public class TreeNodeForVueUtil {


    private String title;
    private String id;
    private String parentId;
    private List<TreeNodeForVueUtil> children;


    private String nameCH;//中文名
    private LocalDateTime mTime;//修改时间
    private String modifier;//修改人

    private String dataSourceName;//数据源名称
    private String databaseName;//数据库名称
    private String dataTableName;//数据表名称

    private String sensitiveTableId;//敏感表的id
    private String metadataTableCode ;//数据表的编码

    public String getSensitiveTableId() {
        return sensitiveTableId;
    }

    public void setSensitiveTableId(String sensitiveTableId) {
        this.sensitiveTableId = sensitiveTableId;
    }

    public String getMetadataTableCode() {
        return metadataTableCode;
    }

    public void setMetadataTableCode(String metadataTableCode) {
        this.metadataTableCode = metadataTableCode;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDataTableName() {
        return dataTableName;
    }

    public void setDataTableName(String dataTableName) {
        this.dataTableName = dataTableName;
    }

    public String getNameCH() {
        return nameCH;
    }

    public void setNameCH(String nameCH) {
        this.nameCH = nameCH;
    }

    public LocalDateTime getmTime() {
        return mTime;
    }

    public void setmTime(LocalDateTime mTime) {
        this.mTime = mTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<TreeNodeForVueUtil> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNodeForVueUtil> children) {
        this.children = children;
    }

    public TreeNodeForVueUtil() {

    }

    public TreeNodeForVueUtil(String title, String id, String parentId, String nameCH,LocalDateTime mTime,
                              String modifier,String dataSourceName, String databaseName, String dataTableName,
                              String sensitiveTableId,String metadataTableCode) {
        this.title = title;
        this.id = id;
        this.parentId = parentId;
        this.nameCH = nameCH;
        this.mTime = mTime;
        this.modifier = modifier;
        this.dataSourceName = dataSourceName;
        this.databaseName = databaseName;
        this.dataTableName = dataTableName;
        this.metadataTableCode = metadataTableCode;
        this.sensitiveTableId = sensitiveTableId;
    }

    public TreeNodeForVueUtil(String title, String id, String parentId) {
        this.title = title;
        this.id = id;
        this.parentId = parentId;
    }

    /**
     * 递归（先得到父节点）再递归
     *
     * @param list
     * @return
     */
    public static List<TreeNodeForVueUtil> getTreeResult(List<TreeNodeForVueUtil> list) {
        List<TreeNodeForVueUtil> listResult = new ArrayList<>();
        for (TreeNodeForVueUtil t : list) {
            if (t.getParentId().equals("0")) {
                listResult.add(t);// 得到父类
            }
        }
        secondTree(list, listResult);
        return listResult;
    }

    /**
     * 父节点获取子节点
     *
     * @param list
     * @param listResult
     * @return
     */

    public static void secondTree(List<TreeNodeForVueUtil> list, List<TreeNodeForVueUtil> listResult) {
        for (TreeNodeForVueUtil tResult : listResult) {
            List<TreeNodeForVueUtil> childrens = new ArrayList<>();
            for (TreeNodeForVueUtil t : list) {
                if (t.getParentId().equals("0")) {
                    continue;
                }
                if (tResult.getId().equals(t.getParentId())) {
                    childrens.add(t);
                }
            }
            tResult.setChildren(childrens);
            if (!tResult.getChildren().isEmpty()) {
                secondTree(list, tResult.getChildren());
            }
        }

    }

    //使用方法参考
  /*  List< TreeNodeForVueUtile> tree = new ArrayList<>();
        for (EventType e:list //原来的数据进行转换，list是原来的数据
                ) {
        tree.add(new TreeNodeForVueUtile(e.getName(), e.getEventTypeId(), e.getParentId()));
    }
    List<TreeNodeForVueUtile> result =  TreeNodeForVueUtile.getTreeResult(tree);     //递归得到的结果
    */
}


