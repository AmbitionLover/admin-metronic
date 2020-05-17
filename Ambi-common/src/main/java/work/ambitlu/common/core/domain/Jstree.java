package work.ambitlu.common.core.domain;

import lombok.Data;

import java.util.ArrayList;
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
    private String id;
    private String text;
    private String icon;
    private Map state;
    private List<Jstree> children;
}
