/**

 @Name：layuiAdmin 菜单管理
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL

 */


layui.define(['treeTable','layer','code','form'], function(exports){
    var o = layui.$,
        form = layui.form,
        layer = layui.layer,
        view = layui.view,
        admin = layui.admin,
        treeTable = layui.treeTable;

    var	re = treeTable.render({
        elem: '#tree-table',
        url : ctx + 'system/menu/list',

        // data: [{"menuId":1,"parentId":0,"s":"123","title":"1-1"},{"menuId":2,"parentId":0,"title":"1-2"},{"menuId":3,"parentId":0,"title":"1-3"},{"menuId":4,"parentId":1,"title":"1-1-1"},{"menuId":5,"parentId":1,"title":"1-1-2"},{"menuId":6,"parentId":2,"title":"1-2-1"},{"menuId":7,"parentId":2,"title":"1-2-3"},{"menuId":8,"parentId":3,"title":"1-3-1"},{"menuId":9,"parentId":3,"title":"1-3-2"},{"menuId":10,"parentId":4,"title":"1-1-1-1"},{"menuId":11,"parentId":4,"title":"1-1-1-2"}],
        icon_key: 'menuName',
        is_checkbox: true,
        checked: {
            key: 'menuId',
            data: [0,1,4,10,11,5,2,6,7,3,8,9],
        },
        end: function(e){
            form.render();
        },
        cols: [
            {
                key: 'menuName',
                title: '名称',
                width: '100px',
                template: function(item){
                    if(item.level == 0){
                        return '<span style="color:red;">'+item.menuName+'</span>';
                    }else if(item.level == 1){
                        return '<span style="color:green;">'+item.menuName+'</span>';
                    }else if(item.level == 2){
                        return '<span style="color:#aaa;">'+item.menuName+'</span>';
                    }
                }
            },
            {
                key: 'menuId',
                title: 'ID',
                width: '100px',
                align: 'center',
            },
            {
                key: 'parentId',
                title: '父ID',
                width: '100px',
                align: 'center',
            },
            {
                title: '开关',
                width: '100px',
                align: 'center',
                template: function(item){
                    return '<input type="checkbox" name="close" lay-skin="switch" lay-text="ON|OFF">';
                }
            },
            {
                title: '操作',
                align: 'center',
                template: function(item){
                    return '<a lay-filter="add">添加</a> | <a lay-filter="edit">编辑</a>';
                }
            }
        ]
    });



    // 监听展开关闭
    treeTable.on('tree(flex)',function(data){
        layer.msg(JSON.stringify(data));
    })
    // 监听checkbox选择
    treeTable.on('tree(box)',function(data){
        if(o(data.elem).parents('#tree-table1').length){
            var text = [];
            o(data.elem).parents('#tree-table1').find('.cbx.layui-form-checked').each(function(){
                o(this).parents('[data-pid]').length && text.push(o(this).parents('td').next().find('span').text());
            })
            o(data.elem).parents('#tree-table1').prev().find('input').val(text.join(','));
        }
        layer.msg(JSON.stringify(data));
    })
    // 监听自定义
    treeTable.on('tree(add)',function(data){
        layer.msg(JSON.stringify(data));
    })
    // 监听编辑事件
    treeTable.on('tree(edit)',function(data){
        // layer.msg(data.item.menuId);
        admin.popup({
            title: '编辑菜单'
            ,area: ['420px', '450px']
            ,id: 'LAY-popup-user-add'
            ,success: function(layero, index){
                view(this.id).render('system/menu/edit/'+data.item.menuId, data).done(function(){
                    form.render(null, 'layuiadmin-form-admin');

                    //监听提交
                    form.on('submit(LAY-menu-back-submit)', function(data){
                        var field = data.field; //获取提交的字段

                        //提交 Ajax 成功后，关闭当前弹层并重载表格
                        //$.ajax({});
                        layui.table.reload('LAY-user-back-manage'); //重载表格
                        layer.close(index); //执行关闭
                    });
                });
            }
        });
    })
    // 获取选中值，返回值是一个数组（定义的primary_key参数集合）
    o('.get-checked').click(function(){
        layer.msg('选中参数'+treeTable.checked(re).join(','))
    })
    // 刷新重载树表（一般在异步处理数据后刷新显示）
    o('.refresh').click(function(){
        re.data.push({"id":50,"pid":0,"title":"1-4"},{"id":51,"pid":50,"title":"1-4-1"});
        treeTable.render(re);
    })
    // 全部展开
    o('.open-all').click(function(){
        treeTable.openAll(re);
    })
    // 全部关闭
    o('.close-all').click(function(){
        treeTable.closeAll(re);
    })
    // 随机更换小图标
    o('.change-icon').click(function(){
        var arr = [
            {
                open: 'layui-icon layui-icon-set',
                close: 'layui-icon layui-icon-set-fill',
                left: 16,
            },
            {
                open: 'layui-icon layui-icon-rate',
                close: 'layui-icon layui-icon-rate-solid',
                left: 16,
            },
            {
                open: 'layui-icon layui-icon-tread',
                close: 'layui-icon layui-icon-praise',
                left: 16,
            },
            {
                open: 'layui-icon layui-icon-camera',
                close: 'layui-icon layui-icon-camera-fill',
                left: 16,
            },
            {
                open: 'layui-icon layui-icon-user',
                close: 'layui-icon layui-icon-group',
                left: 16,
            },
        ];
        var round = Math.round(Math.random()*(arr.length - 1));
        re.icon = arr[round];
        treeTable.render(re);
    })
    o('#tree1').on('click','[data-down]',function(){
        o(this).find('span').length && o(this).parents('.layui-unselect').find('input').val(o(this).text());
    })
    o('.layui-select-title').click(function(){
        o(this).parent().hasClass('layui-form-selected') ? o(this).next().hide() : o(this).next().show(),o(this).parent().toggleClass('layui-form-selected');
    })
    o(document).on("click", function(i) {
        !o(i.target).parent().hasClass('layui-select-title') && !o(i.target).parents('table').length && !(!o(i.target).parents('table').length && o(i.target).hasClass('layui-icon')) && o(".layui-form-select").removeClass("layui-form-selected").find('.layui-anim').hide();
    })


    exports('menu', {})
});