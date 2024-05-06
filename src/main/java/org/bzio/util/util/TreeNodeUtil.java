package org.bzio.util.util;

import org.bzio.util.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 树结构构建工具类
 * 例：菜单、单位
 * <p>
 * 思路：
 * 1.查询到根节点
 * 2.根据给出的节点获取到下级节点，添加到节点对象的children属性中
 * 3.利用递归依次获取到重复2操作
 * 4.返回根节点（根节点中children属性包含其子级）
 * 5.递归完成每一级节点根据children关联起来
 *
 * @author snow
 */
public class TreeNodeUtil {

    /**
     * stream方式构建树结构
     */
    public static <T extends TreeNode<T>> List<T> buildTreeList(List<T> treeNodeList) {
        // 获取根节点
        // 过滤`j的pid`和`i的id`相同的数据，剩余的都是pid不在列表的id里查不到的（即为最上级根节点）
        // 遍历根节点，每个根节点k设置子级
        return treeNodeList.stream().filter(i -> treeNodeList.stream().noneMatch(j -> Objects.equals(j.getId(), i.getPid()))).peek(k ->
                // 子级集合添加到根节点对象
                k.setChildren(getChildrenList(k, treeNodeList))
        ).collect(Collectors.toList());
    }

    /**
     * 获取子节点列表
     *
     * @param node         根节点
     * @param treeNodeList 所有节点信息
     */
    public static <T extends TreeNode<T>> List<T> getChildrenList(T node, List<T> treeNodeList) {
        // 过滤出node节点的子节点
        // 遍历子节点，递归查询子节点j的子级，直到不能匹配到子级（Objects.equals(item.getPid(), node.getId())不满足）
        // 返回子节点集合
        return treeNodeList.stream().filter(i -> Objects.equals(i.getPid(), node.getId())).peek(j ->
                // 递归设置子级的下一级
                j.setChildren(getChildrenList(j, treeNodeList))
        ).collect(Collectors.toList());
    }
    
    /**
     * 常规递归方法构建树结构
     */
    public static <T extends TreeNode<T>> List<T> buildTreeList2(List<T> treeNodeList) {
        // 结果集
        List<T> returnList = new ArrayList<>();
        // 临时集合，暂存节点id
        List<String> tempList = new ArrayList<>();

        // 遍历节点集合，id添加进入临时集合
        for (T node : treeNodeList) {
            tempList.add(node.getId());
        }
        // 处理树结构
        for (T node : treeNodeList) {
            // tempList不包含dept父级id时，为根节点
            if (!tempList.contains(node.getPid())) {
                // 如果是根节点, 遍历该父节点的所有子节点
                // set节点node的子级节点集合
                recursionFn(treeNodeList, node);
                // 将set子级集合children的节点返回
                returnList.add(node);
            }
        }

        // 为空，没有关联数据，则直接返回原集合
        if (returnList.isEmpty()) {
            returnList = treeNodeList;
        }
        return returnList;
    }

    /**
     * 递归列表
     *  子级通过setChildren设置
     */
    private static <T extends TreeNode<T>> void recursionFn(List<T> list, T node) {
        // 得到子节点列表
        List<T> childList = getChildList(list, node);
        // set节点node的子级集合
        node.setChildren(childList);

        // 递归查询节点t每个子级
        for (T tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private static <T extends TreeNode<T>> List<T> getChildList(List<T> list, T node) {
        // 节点node子级集合
        List<T> children = new ArrayList<>();

        // 遍历节点列表
        for (T t : list) {
            // 当前循环中节点父级id不为空，并且父级id和节点node的id相同则为node的子级
            if (StringUtil.isNotEmpty(t.getPid()) && Objects.equals(t.getPid(), node.getId())) {
                children.add(t);
            }
        }
        return children;
    }

    /**
     * 判断是否有子节点
     */
    private static <T extends TreeNode<T>> boolean hasChild(List<T> list, T node) {
        return !getChildList(list, node).isEmpty();
    }
}
