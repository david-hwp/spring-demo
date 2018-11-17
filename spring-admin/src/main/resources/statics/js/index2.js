layui.use(['jquery', 'layer', 'element'], function () {
    window.jQuery = window.$ = layui.jquery;
    window.layer = layui.layer;
});

//生成菜单
var menuItem = Vue.extend({
    name: 'menu-item',
    props: {item: {}},
    template: [
        '<li v-miniNav>',
        '<a v-if="item.type === 0" href="#">',
        '<i v-if="item.icon != null" :class="item.icon"></i>',
        '<span class="nav-label">{{item.name}}</span>',
        '<span class="fa arrow"></span>',
        '</a>',
        '<ul v-if="item.type === 0" class="nav nav-second-level">',
        '<menu-item :item="item" v-for="item in item.list"></menu-item>',
        '</ul>',
        '<a  v-if="item.type === 1" class="J_menuItem" :href="item.url" v-showTab :data-index="item.id"><i v-if="item.icon != null" :class="item.icon"></i><i v-else class="fa fa-circle"></i>{{item.name}}</a>',
        '</li>'
    ].join('')
});

Vue.directive('menu', {
    inserted: function (el) {
        $(el).parent().metisMenu();
    }
});

Vue.directive('showTab', {
    inserted: function (el) {
        $(el).on('click', showMenuItem);
    }
});

Vue.directive('miniNav', {
    inserted: function (el) {
        $(el).on('click', function () {
            if ($('body').hasClass('mini-navbar')) {
                NavToggle();
            }
        });
    }
});

//注册菜单组件
Vue.component('menuItem', menuItem);

var vm = new Vue({
    el: '#layui_layout',
    data: {
        user: {},
        menuList: {},
        password: '',
        newPassword: '',
        navTitle: "首页"
    },
    methods: {
        getMenuList: function () {
            $.getJSON("sys/menu/nav", function (r) {
                vm.menuList = r.menuList;
            });
        },
        getUser: function () {
            $.getJSON("sys/user/info?_" + $.now(), function (r) {
                vm.user = r.user;
            });
        },
        updatePassword: function () {
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "修改密码",
                area: ['550px', '270px'],
                shadeClose: false,
                content: jQuery("#passwordLayer"),
                btn: ['修改', '取消'],
                btn1: function (index) {
                    var data = "password=" + vm.password + "&newPassword=" + vm.newPassword;
                    $.ajax({
                        type: "POST",
                        url: "sys/user/password",
                        data: data,
                        dataType: "json",
                        success: function (result) {
                            if (result.code == 0) {
                                layer.close(index);
                                layer.alert('修改成功', function (index) {
                                    location.reload();
                                });
                            } else {
                                layer.alert(result.msg);
                            }
                        }
                    });
                }
            });
        }
    },
    created: function () {
        this.getMenuList();
        this.getUser();
    }
});
