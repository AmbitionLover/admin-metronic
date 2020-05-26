package work.ambitlu.common.core.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jstree
 *
 * @author Ambi
 * @Description:
 * @ate: 2020/5/15 16:42
 * @since JDK 1.11
 */
@Data
public class Jstree<T> {
    /** 节点ID */
    private String id;
    /** 父亲节点 */
    private String parent;
    /** 节点标题 */
    private String text;
    /** 节点图表 */
    private String icon;
    /** 节点状态 */
    private Map<String,Boolean> state;

    public Jstree()
    {
        state = new HashMap();

    }
    private List<Jstree> children;
}
