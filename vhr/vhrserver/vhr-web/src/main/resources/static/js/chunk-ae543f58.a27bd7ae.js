(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-ae543f58"],{"1fe7":function(e,t,n){"use strict";n.r(t);var i=function(){var e=this,t=e._self._c;return t("div",[e._v("\n  系统管理\n")])},l=[],a={name:"SysCfg"},s=a,o=n("2877"),r=Object(o["a"])(s,i,l,!1,null,"a6c81c2e",null);t["default"]=r.exports},"418a":function(e,t,n){"use strict";n.r(t);n("7f7f");var i=function(){var e=this,t=e._self._c;return t("div",[t("div",[t("div",{staticStyle:{"margin-top":"10px",display:"flex","justify-content":"space-between"}},[t("div",[t("el-input",{staticStyle:{width:"400px","margin-right":"10px"},attrs:{placeholder:"通过用户名搜索用户...","prefix-icon":"el-icon-search"},nativeOn:{keydown:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.doSearch.apply(null,arguments)}},model:{value:e.keywords,callback:function(t){e.keywords=t},expression:"keywords"}}),t("el-button",{attrs:{icon:"el-icon-search",type:"primary"},on:{click:e.doSearch}},[e._v("搜索")])],1),t("div",[t("el-button",{attrs:{type:"primary",icon:"el-icon-plus"},on:{click:function(t){return e.showAddHrView()}}},[e._v("\n            添加用户\n          ")])],1)])]),t("el-dialog",{attrs:{title:e.title,visible:e.dialogVisible,width:"80%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[t("div",[t("el-form",{ref:"hrForm",attrs:{model:e.hr,rules:e.rules,"label-position":"right"}},[t("el-row",[t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{label:"账号:",prop:"username"}},[t("el-input",{staticStyle:{width:"150px"},attrs:{size:"mini","prefix-icon":"el-icon-edit",placeholder:"请输入员工账号"},model:{value:e.hr.username,callback:function(t){e.$set(e.hr,"username",t)},expression:"hr.username"}})],1)],1),t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{label:"姓名:",prop:"name"}},[t("el-input",{staticStyle:{width:"150px"},attrs:{size:"mini","prefix-icon":"el-icon-edit",placeholder:"请输入员工姓名"},model:{value:e.hr.name,callback:function(t){e.$set(e.hr,"name",t)},expression:"hr.name"}})],1)],1)],1),t("el-row",[t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{label:"手机:",prop:"phone"}},[t("el-input",{staticStyle:{width:"200px"},attrs:{size:"mini","prefix-icon":"el-icon-phone",placeholder:"手机"},model:{value:e.hr.phone,callback:function(t){e.$set(e.hr,"phone",t)},expression:"hr.phone"}})],1)],1),t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{label:"地址:",prop:"address"}},[t("el-input",{staticStyle:{width:"200px"},attrs:{size:"mini","prefix-icon":"el-icon-edit",placeholder:"请输入联系地址"},model:{value:e.hr.address,callback:function(t){e.$set(e.hr,"address",t)},expression:"hr.address"}})],1)],1)],1),t("el-row",[t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{label:"用户状态:",prop:"enabled"}},[t("el-switch",{staticStyle:{"padding-top":"5px"},attrs:{"active-text":"启用","active-color":"#13ce66","inactive-color":"#ff4949","inactive-text":"禁用"},on:{change:function(t){return e.enabledChangeInfo(e.hr)}},model:{value:e.hr.enabled,callback:function(t){e.$set(e.hr,"enabled",t)},expression:"hr.enabled"}})],1)],1),t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{label:"备注:",prop:"remark"}},[t("el-input",{attrs:{type:"textarea",row:2,size:"mini"},model:{value:e.hr.remark,callback:function(t){e.$set(e.hr,"remark",t)},expression:"hr.remark"}})],1)],1)],1)],1)],1),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:e.doAddHr}},[e._v("确 定")])],1)]),t("div",{staticClass:"hr-container"},e._l(e.hrs,(function(n,i){return t("el-card",{key:i,staticClass:"hr-card"},[t("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[t("span",[e._v(e._s(n.name))]),t("el-button",{staticStyle:{float:"right",padding:"3px 3px",color:"#e30007"},attrs:{type:"text",icon:"el-icon-delete"},on:{click:function(t){return e.deleteHr(n)}}},[e._v("删除")]),t("el-button",{staticStyle:{float:"right",padding:"3px 3px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:function(t){return e.showEditHrView(n)}}},[e._v("编辑")]),t("el-button",{staticStyle:{float:"right",padding:"3px 3px"},attrs:{type:"info",icon:""},on:{click:function(t){return e.resetPassword(n)}}},[e._v("重置密码")])],1),t("div",[t("div",{staticClass:"img-container"},[t("img",{staticClass:"userface-img",attrs:{src:n.userface?n.userface:e.userface,alt:n.name,title:n.name}})]),t("div",{staticClass:"userinfo-container"},[t("div",[e._v("用户名："+e._s(n.name))]),t("div",[e._v("手机号码："+e._s(n.phone))]),t("div",[e._v("地址："+e._s(n.address))]),t("div",[e._v("用户状态：\n                        "),t("el-switch",{attrs:{"active-text":"启用","active-color":"#13ce66","inactive-color":"#ff4949","inactive-text":"禁用"},on:{change:function(t){return e.enabledChange(n)}},model:{value:n.enabled,callback:function(t){e.$set(n,"enabled",t)},expression:"hr.enabled"}})],1),t("div",[e._v("用户角色：\n                        "),e._l(n.roles,(function(n,i){return t("el-tag",{key:i,staticStyle:{"margin-right":"4px"},attrs:{type:"success"}},[e._v(e._s(n.nameZh)+"\n                        ")])})),t("el-popover",{attrs:{placement:"right",title:"角色列表",width:"200",trigger:"click"},on:{show:function(t){return e.showPop(n)},hide:function(t){return e.hidePop(n)}}},[t("el-select",{attrs:{multiple:"",placeholder:"请选择"},model:{value:e.selectedRoles,callback:function(t){e.selectedRoles=t},expression:"selectedRoles"}},e._l(e.allroles,(function(e,n){return t("el-option",{key:n,attrs:{label:e.nameZh,value:e.id}})})),1),t("el-button",{attrs:{slot:"reference",icon:"el-icon-more",type:"text"},slot:"reference"})],1)],2),t("div",[e._v("备注："+e._s(n.remark))])])])])})),1)],1)},l=[],a=(n("ac6a"),n("0fb7"),n("450d"),n("f529")),s=n.n(a),o={name:"SysHr",data:function(){return{userface:this.$ELEMENT.userface,keywords:"",hrs:[],selectedRoles:[],allroles:[],dialogVisible:!1,title:"",hr:{id:null,username:"",name:"",phone:"",address:"",enabled:!0,remark:""},rules:{username:[{required:!0,message:"请输入用户账号",trigger:"blur"}],name:[{required:!0,message:"请输入用户名",trigger:"blur"}],phone:[{required:!0,message:"请输入手机",trigger:"blur"}],address:[{required:!1,message:"请输入输入地址",trigger:"blur"}],remark:[{required:!1,message:"请输入备注信息",trigger:"blur"}]}}},mounted:function(){this.initHrs()},methods:{deleteHr:function(e){var t=this;this.$confirm("此操作将永久删除【"+e.name+"】, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){t.deleteRequest("/system/hr/"+e.id).then((function(e){e&&t.initHrs()}))})).catch((function(){s.a.info({message:"已取消删除"})}))},resetPassword:function(e){var t=this;this.$confirm("重置【"+e.name+"】的密码为：ghk, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){t.putRequest("/system/hr/reset/"+e.id).then((function(e){e&&t.initHrs()}))})).catch((function(){s.a.info({message:"已取消重置密码",duration:500})}))},editHr:function(e){},showEditHrView:function(e){this.emptyHr(),this.title="编辑操作员信息",this.hr=e,this.dialogVisible=!0},showAddHrView:function(){this.emptyHr(),this.title="添加操作员",this.dialogVisible=!0},emptyHr:function(){this.hr={id:null,username:"",name:"",phone:"",address:"",enabled:!0,remark:""}},doAddHr:function(){var e=this;this.hr.id?this.$refs["hrForm"].validate((function(t){t&&e.putRequest("/system/hr/",e.hr).then((function(t){t&&(e.dialogVisible=!1,e.initHrs())}))})):this.$refs["hrForm"].validate((function(t){t&&e.postRequest("/system/hr/",e.hr).then((function(t){t&&(e.dialogVisible=!1,e.initHrs())}))}))},doSearch:function(){this.initHrs()},hidePop:function(e){var t=this,n=[];Object.assign(n,e.roles);var i=!1;if(n.length!=this.selectedRoles.length)i=!0;else{for(var l=0;l<n.length;l++)for(var a=n[l],s=0;s<this.selectedRoles.length;s++){var o=this.selectedRoles[s];if(a.id==o){n.splice(l,1),l--;break}}0!=n.length&&(i=!0)}if(i){var r="/system/hr/role?hrid="+e.id;this.selectedRoles.forEach((function(e){r+="&rids="+e})),this.putRequest(r).then((function(e){e&&t.initHrs()}))}},showPop:function(e){var t=this;this.initAllRoles();var n=e.roles;this.selectedRoles=[],n.forEach((function(e){t.selectedRoles.push(e.id)}))},enabledChangeInfo:function(e){},enabledChange:function(e){var t=this;delete e.roles,this.putRequest("/system/hr/",e).then((function(e){e&&t.initHrs()}))},initAllRoles:function(){var e=this;this.getRequest("/system/hr/roles").then((function(t){t&&(e.allroles=t)}))},initHrs:function(){var e=this;this.getRequest("/system/hr/?keywords="+this.keywords).then((function(t){t&&(e.hrs=t)}))}}},r=o,c=(n("8852"),n("2877")),d=Object(c["a"])(r,i,l,!1,null,null,null);t["default"]=d.exports},"595b":function(e,t,n){"use strict";n("eeb1")},"802f":function(e,t,n){"use strict";n("85a6")},"85a6":function(e,t,n){},8608:function(e,t,n){"use strict";n.r(t);var i=function(){var e=this,t=e._self._c;return t("div",[e._v("\n    初始化数据库\n")])},l=[],a={name:"SysInit"},s=a,o=n("2877"),r=Object(o["a"])(s,i,l,!1,null,"545f1afe",null);t["default"]=r.exports},"864e":function(e,t,n){"use strict";n.r(t);var i=function(){var e=this,t=e._self._c;return t("div",[e._v("\n    操作日志管理\n")])},l=[],a={name:"SysLog"},s=a,o=n("2877"),r=Object(o["a"])(s,i,l,!1,null,"ff6f3968",null);t["default"]=r.exports},8751:function(e,t,n){"use strict";n("9747")},8852:function(e,t,n){"use strict";n("f61b")},"8d67":function(e,t,n){"use strict";n.r(t);var i=function(){var e=this,t=e._self._c;return t("div",[t("el-tabs",{attrs:{type:"card"},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[t("el-tab-pane",{attrs:{label:"部门管理",name:"depmana"}},[t("DepMana")],1),t("el-tab-pane",{attrs:{label:"职位管理",name:"posmana"}},[t("PosMana")],1),t("el-tab-pane",{attrs:{label:"职称管理",name:"joblevelmana"}},[t("JobLevelMana")],1),t("el-tab-pane",{attrs:{label:"权限组",name:"permissmana"}},[t("PermissMana")],1)],1)],1)},l=[],a=(n("7f7f"),function(){var e=this,t=e._self._c;return t("div",{staticStyle:{width:"500px"}},[t("el-input",{attrs:{placeholder:"请输入部门名称进行搜索...","prefix-icon":"el-icon-search"},model:{value:e.filterText,callback:function(t){e.filterText=t},expression:"filterText"}}),t("el-tree",{ref:"tree",attrs:{data:e.deps,props:e.defaultProps,"expand-on-click-node":!1,"filter-node-method":e.filterNode},scopedSlots:e._u([{key:"default",fn:function(n){n.node;var i=n.data;return t("span",{staticClass:"custom-tree-node",staticStyle:{display:"flex","justify-content":"space-between",width:"100%"}},[t("span",[e._v(e._s(i.name))]),t("span",[t("el-button",{staticClass:"depBtn",attrs:{type:"primary",size:"mini"},on:{click:function(){return e.showAddDepView(i)}}},[e._v("\n          添加部门\n        ")]),t("el-button",{staticClass:"depBtn",attrs:{type:"danger",size:"mini"},on:{click:function(){return e.deleteDep(i)}}},[e._v("\n          删除部门\n        ")])],1)])}}])}),t("el-dialog",{attrs:{title:"添加部门",visible:e.dialogVisible,width:"30%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[t("div",[t("table",[t("tr",[t("td",[t("el-tag",[e._v("上级部门")])],1),t("td",[e._v(e._s(e.pname))])]),t("tr",[t("td",[t("el-tag",[e._v("部门名称")])],1),t("td",[t("el-input",{attrs:{placeholder:"请输入部门名称..."},model:{value:e.dep.name,callback:function(t){e.$set(e.dep,"name",t)},expression:"dep.name"}})],1)])])]),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:e.doAddDep}},[e._v("确 定")])],1)])],1)}),s=[],o={name:"DepMana",data:function(){return{dialogVisible:!1,filterText:"",dep:{name:"",parentId:-1},pname:"",deps:[],defaultProps:{children:"children",label:"name"}}},watch:{filterText:function(e){this.$refs.tree.filter(e)}},mounted:function(){this.initDeps()},methods:{initDep:function(){this.dep={name:"",parentId:-1},this.pname=""},addDep2Deps:function(e,t){for(var n=0;n<e.length;n++){var i=e[n];if(i.id==t.parentId)return i.children=i.children.concat(t),void(i.children.length>0&&(i.parent=!0));this.addDep2Deps(i.children,t)}},doAddDep:function(){var e=this;this.postRequest("/system/basic/department/",this.dep).then((function(t){t&&(e.addDep2Deps(e.deps,t.obj),e.dialogVisible=!1,e.initDep())}))},removeDepFromDeps:function(e,t,n){for(var i=0;i<t.length;i++){var l=t[i];if(l.id==n)return t.splice(i,1),void(0==t.length&&(e.parent=!1));this.removeDepFromDeps(l,l.children,n)}},deleteDep:function(e){var t=this;e.parent?this.$message.error("父部门删除失败"):this.$confirm("此操作将永久删除【"+e.name+"】部门, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){t.deleteRequest("/system/basic/department/"+e.id).then((function(n){n&&t.removeDepFromDeps(null,t.deps,e.id)}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})}))},showAddDepView:function(e){this.pname=e.name,this.dep.parentId=e.id,this.dialogVisible=!0},initDeps:function(){var e=this;this.getRequest("/system/basic/department/").then((function(t){t&&(e.deps=t)}))},filterNode:function(e,t){return!e||-1!==t.name.indexOf(e)}}},r=o,c=(n("802f"),n("2877")),d=Object(c["a"])(r,a,s,!1,null,null,null),u=d.exports,p=function(){var e=this,t=e._self._c;return t("div",[t("div",[t("el-input",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"addPosInput",attrs:{size:"small","element-loading-text":"正在加载...","element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(0, 0, 0, 0.8)",placeholder:"添加职位...","prefix-icon":"el-icon-plus"},nativeOn:{keydown:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.addPosition.apply(null,arguments)}},model:{value:e.pos.name,callback:function(t){e.$set(e.pos,"name",t)},expression:"pos.name"}}),t("el-button",{attrs:{icon:"el-icon-plus",size:"small",type:"primary"},on:{click:e.addPosition}},[e._v("添加")])],1),t("div",{staticClass:"posManaMain"},[t("el-table",{staticStyle:{width:"70%"},attrs:{data:e.positions,border:"",size:"small",stripe:""},on:{"selection-change":e.handleSelectionChange}},[t("el-table-column",{attrs:{type:"selection",width:"55"}}),t("el-table-column",{attrs:{prop:"id",label:"编号",width:"55"}}),t("el-table-column",{attrs:{prop:"name",label:"职位名称",width:"180"}}),t("el-table-column",{attrs:{prop:"createDate",width:"150",label:"创建时间"}}),t("el-table-column",{attrs:{label:"是否启用"},scopedSlots:e._u([{key:"default",fn:function(n){return[n.row.enabled?t("el-tag",{attrs:{size:"small",type:"success"}},[e._v("已启用")]):t("el-tag",{attrs:{size:"small",type:"danger"}},[e._v("未启用")])]}}])}),t("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(n){return[t("el-button",{attrs:{size:"mini"},on:{click:function(t){return e.showEditView(n.$index,n.row)}}},[e._v("编辑\n                      ")]),t("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(t){return e.handleDelete(n.$index,n.row)}}},[e._v("删除\n                      ")])]}}])})],1),t("el-button",{staticStyle:{"margin-top":"8px"},attrs:{type:"danger",size:"small",disabled:0==e.multipleSelection.length},on:{click:e.deleteMany}},[e._v("批量删除\n          ")])],1),t("el-dialog",{attrs:{title:"修改职位",visible:e.dialogVisible,width:"30%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[t("div",[t("div",[t("el-tag",[e._v("职位名称")]),t("el-input",{staticClass:"updatePosInput",attrs:{size:"small"},model:{value:e.updatePos.name,callback:function(t){e.$set(e.updatePos,"name",t)},expression:"updatePos.name"}})],1),t("div",[t("el-tag",[e._v("是否启用")]),t("el-switch",{attrs:{"active-text":"启用","inactive-text":"禁用"},model:{value:e.updatePos.enabled,callback:function(t){e.$set(e.updatePos,"enabled",t)},expression:"updatePos.enabled"}})],1)]),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{attrs:{size:"small"},on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{size:"small",type:"primary"},on:{click:e.doUpdate}},[e._v("确 定")])],1)])],1)},f=[],m=(n("ac6a"),{name:"PosMana",data:function(){return{pos:{name:""},dialogVisible:!1,loading:!1,updatePos:{name:"",enabled:!1},multipleSelection:[],positions:[]}},mounted:function(){this.initPositions()},methods:{deleteMany:function(){var e=this;this.$confirm("此操作将永久删除【"+this.multipleSelection.length+"】条记录, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){var t="?";e.multipleSelection.forEach((function(e){t+="ids="+e.id+"&"})),e.deleteRequest("/system/basic/pos/"+t).then((function(t){t&&e.initPositions()}))})).catch((function(){e.$message({type:"info",message:"已取消删除"})}))},handleSelectionChange:function(e){this.multipleSelection=e},addPosition:function(){var e=this;this.pos.name?this.postRequest("/system/basic/pos/",this.pos).then((function(t){t&&(e.initPositions(),e.pos.name="")})):this.$message.error("职位名称不可以为空")},showEditView:function(e,t){Object.assign(this.updatePos,t),this.dialogVisible=!0},doUpdate:function(){var e=this;this.putRequest("/system/basic/pos/",this.updatePos).then((function(t){t&&(e.initPositions(),e.updatePos.name="",e.dialogVisible=!1)}))},handleDelete:function(e,t){var n=this;this.$confirm("此操作将永久删除【"+t.name+"】职位, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){n.deleteRequest("/system/basic/pos/"+t.id).then((function(e){e&&n.initPositions()}))})).catch((function(){n.$message({type:"info",message:"已取消删除"})}))},initPositions:function(){var e=this;this.loading=!0,this.getRequest("/system/basic/pos/").then((function(t){e.loading=!1,t&&(e.positions=t)}))}}}),h=m,b=(n("595b"),Object(c["a"])(h,p,f,!1,null,null,null)),v=b.exports,g=function(){var e=this,t=e._self._c;return t("div",[t("div",[t("el-input",{staticStyle:{width:"300px"},attrs:{size:"small","prefix-icon":"el-icon-plus",placeholder:"添加职称..."},model:{value:e.jl.name,callback:function(t){e.$set(e.jl,"name",t)},expression:"jl.name"}}),t("el-select",{staticStyle:{"margin-left":"5px","margin-right":"5px"},attrs:{placeholder:"职称等级",size:"small"},model:{value:e.jl.titleLevel,callback:function(t){e.$set(e.jl,"titleLevel",t)},expression:"jl.titleLevel"}},e._l(e.titleLevels,(function(e){return t("el-option",{key:e,attrs:{label:e,value:e}})})),1),t("el-button",{attrs:{icon:"el-icon-plus",type:"primary",size:"small"},on:{click:e.addJobLevel}},[e._v("添加")])],1),t("div",{staticStyle:{"margin-top":"10px"}},[t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{width:"80%"},attrs:{data:e.jls,border:"","element-loading-text":"正在加载...","element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(0, 0, 0, 0.8)",size:"small"},on:{"selection-change":e.handleSelectionChange}},[t("el-table-column",{attrs:{type:"selection",width:"55"}}),t("el-table-column",{attrs:{prop:"id",label:"编号",width:"55"}}),t("el-table-column",{attrs:{prop:"name",label:"职称名称",width:"150"}}),t("el-table-column",{attrs:{prop:"titleLevel",label:"职称级别"}}),t("el-table-column",{attrs:{prop:"createDate",label:"创建时间"}}),t("el-table-column",{attrs:{label:"是否启用"},scopedSlots:e._u([{key:"default",fn:function(n){return[n.row.enabled?t("el-tag",{attrs:{type:"success"}},[e._v("已启用")]):t("el-tag",{attrs:{type:"danger"}},[e._v("未启用")])]}}])}),t("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(n){return[t("el-button",{attrs:{size:"small"},on:{click:function(t){return e.showEditView(n.row)}}},[e._v("编辑")]),t("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(t){return e.deleteHandler(n.row)}}},[e._v("删除")])]}}])})],1),t("el-button",{staticStyle:{"margin-top":"10px"},attrs:{type:"danger",size:"small",disabled:0==e.multipleSelection.length},on:{click:e.deleteMany}},[e._v("批量删除\n          ")])],1),t("el-dialog",{attrs:{title:"修改职称",visible:e.dialogVisible,width:"30%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[t("div",[t("table",[t("tr",[t("td",[t("el-tag",[e._v("职称名")])],1),t("td",[t("el-input",{attrs:{size:"small"},model:{value:e.updateJl.name,callback:function(t){e.$set(e.updateJl,"name",t)},expression:"updateJl.name"}})],1)]),t("tr",[t("td",[t("el-tag",[e._v("职称级别")])],1),t("td",[t("el-select",{staticStyle:{"margin-left":"5px","margin-right":"5px"},attrs:{placeholder:"职称等级",size:"small"},model:{value:e.updateJl.titleLevel,callback:function(t){e.$set(e.updateJl,"titleLevel",t)},expression:"updateJl.titleLevel"}},e._l(e.titleLevels,(function(e){return t("el-option",{key:e,attrs:{label:e,value:e}})})),1)],1)]),t("tr",[t("td",[t("el-tag",[e._v("是否启用")])],1),t("td",[t("el-switch",{attrs:{"active-text":"启用","inactive-text":"禁用"},model:{value:e.updateJl.enabled,callback:function(t){e.$set(e.updateJl,"enabled",t)},expression:"updateJl.enabled"}})],1)])])]),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{attrs:{size:"small"},on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{size:"small",type:"primary"},on:{click:e.doUpdate}},[e._v("确 定")])],1)])],1)},y=[],x={name:"JobLevelMana",data:function(){return{dialogVisible:!1,loading:!1,multipleSelection:[],updateJl:{name:"",titleLevel:"",enabled:!1},jl:{name:"",titleLevel:""},jls:[],titleLevels:["正高级","副高级","中级","初级","员级"]}},mounted:function(){this.initJls()},methods:{deleteMany:function(){var e=this;this.$confirm("此操作将永久删除【"+this.multipleSelection.length+"】条记录, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){var t="?";e.multipleSelection.forEach((function(e){t+="ids="+e.id+"&"})),e.deleteRequest("/system/basic/joblevel/"+t).then((function(t){t&&e.initJls()}))})).catch((function(){e.$message({type:"info",message:"已取消删除"})}))},doUpdate:function(){var e=this;this.putRequest("/system/basic/joblevel/",this.updateJl).then((function(t){t&&(e.initJls(),e.dialogVisible=!1)}))},handleSelectionChange:function(e){this.multipleSelection=e},showEditView:function(e){Object.assign(this.updateJl,e),this.dialogVisible=!0},deleteHandler:function(e){var t=this;this.$confirm("此操作将永久【"+e.name+"】职称, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){t.deleteRequest("/system/basic/joblevel/"+e.id).then((function(e){e&&t.initJls()}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})}))},addJobLevel:function(){var e=this;this.jl.name&&this.jl.titleLevel?this.postRequest("/system/basic/joblevel/",this.jl).then((function(t){t&&e.initJls()})):this.$message.error("添加字段不可以为空!")},initJls:function(){var e=this;this.loading=!0,this.getRequest("/system/basic/joblevel/").then((function(t){e.loading=!1,t&&(e.jls=t,e.jl={name:"",titleLevel:""})}))}}},k=x,w=Object(c["a"])(k,g,y,!1,null,"a807f2ce",null),_=w.exports,S=function(){var e=this,t=e._self._c;return t("div",[e._v("\n    奖惩规则\n")])},$=[],R={name:"EcMana"},z=R,V=Object(c["a"])(z,S,$,!1,null,"ea92a492",null),q=V.exports,B=function(){var e=this,t=e._self._c;return t("div",{directives:[{name:"loading",rawName:"v-loading",value:e.globalLoading,expression:"globalLoading"}],attrs:{"element-loading-text":"正在添加...","element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(0, 0, 0, 0.8)"}},[t("div",{staticClass:"permissManaTool"},[t("el-input",{attrs:{size:"small",placeholder:"请输入角色英文名"},model:{value:e.role.name,callback:function(t){e.$set(e.role,"name",t)},expression:"role.name"}},[t("template",{slot:"prepend"},[e._v("ROLE_")])],2),t("el-input",{attrs:{size:"small",placeholder:"请输入角色中文名"},nativeOn:{keydown:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.doAddRole.apply(null,arguments)}},model:{value:e.role.nameZh,callback:function(t){e.$set(e.role,"nameZh",t)},expression:"role.nameZh"}}),t("el-button",{attrs:{type:"primary",size:"small",icon:"el-icon-plus"},on:{click:e.doAddRole}},[e._v("添加角色")])],1),t("div",{staticClass:"permissManaMain"},[t("el-collapse",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{"element-loading-text":"正在加载...","element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(0, 0, 0, 0.8)",accordion:""},on:{change:e.change},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},e._l(e.roles,(function(n,i){return t("el-collapse-item",{key:i,attrs:{title:n.nameZh,name:n.id}},[t("el-card",{staticClass:"box-card"},[t("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[t("span",[e._v("可访问的资源")]),t("el-button",{staticStyle:{float:"right",padding:"3px 0",color:"#ff0000"},attrs:{icon:"el-icon-delete",type:"text"},on:{click:function(t){return e.deleteRole(n)}}})],1),t("div",[t("el-tree",{key:i,ref:"tree",refInFor:!0,attrs:{"show-checkbox":"","node-key":"id","default-checked-keys":e.selectedMenus,data:e.allmenus,props:e.defaultProps}}),t("div",{staticStyle:{display:"flex","justify-content":"flex-end"}},[t("el-button",{on:{click:e.cancelUpdate}},[e._v("取消修改")]),t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.doUpdate(n.id,i)}}},[e._v("确认修改")])],1)],1)])],1)})),1)],1)])},j=[],C={name:"PermissMana",data:function(){return{role:{name:"",nameZh:""},allmenus:[],activeName:-1,selectedMenus:[],roles:[],loading:!1,globalLoading:!1,defaultProps:{children:"children",label:"name"}}},mounted:function(){this.initRoles()},methods:{deleteRole:function(e){var t=this;this.$confirm("此操作将永久删除【"+e.nameZh+"】角色, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){t.deleteRequest("/system/basic/permiss/role/"+e.id).then((function(e){e&&t.initRoles()}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})}))},doAddRole:function(){var e=this;this.role.name&&this.role.nameZh?(this.globalLoading=!0,this.postRequest("/system/basic/permiss/role",this.role).then((function(t){e.globalLoading=!1,t&&(e.role.name="",e.role.nameZh="",e.initRoles())}))):this.$message.error("数据不可以为空")},cancelUpdate:function(){this.activeName=-1},doUpdate:function(e,t){var n=this,i=this.$refs.tree[t],l=i.getCheckedKeys(!0),a="/system/basic/permiss/?rid="+e;l.forEach((function(e){a+="&mids="+e})),this.putRequest(a).then((function(e){e&&(n.activeName=-1)}))},change:function(e){e&&(this.initAllMenus(),this.initSelectedMenus(e))},initSelectedMenus:function(e){var t=this;this.getRequest("/system/basic/permiss/mids/"+e).then((function(e){e&&(t.selectedMenus=e)}))},initAllMenus:function(){var e=this;this.getRequest("/system/basic/permiss/menus").then((function(t){t&&(e.allmenus=t)}))},initRoles:function(){var e=this;this.loading=!0,this.getRequest("/system/basic/permiss/").then((function(t){e.loading=!1,t&&(e.roles=t)}))}}},P=C,M=(n("8751"),Object(c["a"])(P,B,j,!1,null,null,null)),O=M.exports,T={name:"SysBasic",data:function(){return{activeName:"depmana"}},components:{DepMana:u,PosMana:v,JobLevelMana:_,EcMana:q,PermissMana:O}},D=T,E=Object(c["a"])(D,i,l,!1,null,"777a20a6",null);t["default"]=E.exports},9401:function(e,t,n){"use strict";n.r(t);n("7f7f");var i=function(){var e=this,t=e._self._c;return t("div",[t("div",[t("div",{staticStyle:{display:"flex","justify-content":"space-between"}},[t("div",[t("el-input",{staticStyle:{width:"350px","margin-right":"10px"},attrs:{placeholder:"请输入基础数据进行搜索，可以直接回车搜索...","prefix-icon":"el-icon-search",clearable:""},on:{clear:e.initBeanlist},nativeOn:{keydown:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.initBeanlist.apply(null,arguments)}},model:{value:e.keyword,callback:function(t){e.keyword=t},expression:"keyword"}}),t("el-button",{attrs:{icon:"el-icon-search",type:"primary"},on:{click:e.initBeanlist}},[e._v("\n          搜索\n        ")])],1),t("div",[t("el-button",{attrs:{type:"primary",icon:"el-icon-plus"},on:{click:e.showAddBeanView}},[e._v("\n          添加基础数据\n        ")])],1)]),t("div",{staticStyle:{"margin-top":"10px"}},[t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{width:"100%"},attrs:{data:e.beanlist,stripe:"",border:"","tooltip-effect":"light","element-loading-text":"正在加载...","element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(0, 0, 0, 0.8)"}},[t("el-table-column",{attrs:{type:"selection",width:"55"}}),t("el-table-column",{attrs:{prop:"name",fixed:"",align:"left",label:"名称"}}),t("el-table-column",{attrs:{prop:"code",label:"编码",align:"left"}}),t("el-table-column",{attrs:{prop:"value",label:"内容",align:"left"}}),t("el-table-column",{attrs:{prop:"sortOrder",width:"95",align:"left",label:"顺序"}}),t("el-table-column",{attrs:{prop:"type",width:"95",align:"left",label:"类别"}}),t("el-table-column",{attrs:{prop:"description",align:"left","show-overflow-tooltip":!0,label:"描述"}}),t("el-table-column",{attrs:{prop:"sys",width:"95",align:"left",formatter:e.formatterText,label:"系统参数"}}),t("el-table-column",{attrs:{fixed:"right",width:"200",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(n){return[t("el-button",{staticStyle:{padding:"3px"},attrs:{size:"mini"},on:{click:function(t){return e.showEditBeanView(n.row)}}},[e._v("编辑")]),1!=n.row.sys?t("el-button",{staticStyle:{padding:"3px"},attrs:{size:"mini",type:"danger"},on:{click:function(t){return e.deleteBean(n.row)}}},[e._v("删除\n            ")]):e._e()]}}])})],1),t("div",{staticStyle:{display:"flex","justify-content":"flex-end"}},[t("el-pagination",{attrs:{background:"",layout:"sizes, prev, pager, next, jumper, ->, total, slot",total:e.total},on:{"current-change":e.currentChange,"size-change":e.sizeChange}})],1)],1),t("el-dialog",{attrs:{title:e.title,visible:e.dialogVisible,width:"30%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[t("div",[t("el-form",{ref:"beanForm",attrs:{model:e.bean,"status-icon":"",rules:e.rules,"label-position":"right"}},[t("el-row",[t("el-col",{attrs:{span:24}},[t("el-form-item",{attrs:{label:"名称:",prop:"name"}},[t("el-input",{attrs:{size:"mini","prefix-icon":"el-icon-edit",placeholder:"请输入名称"},model:{value:e.bean.name,callback:function(t){e.$set(e.bean,"name",t)},expression:"bean.name"}})],1)],1)],1),t("el-row",[t("el-col",{attrs:{span:24}},[t("el-form-item",{attrs:{label:"编码:",prop:"code"}},[t("el-input",{attrs:{size:"mini","prefix-icon":"el-icon-edit",placeholder:"请输入编码"},model:{value:e.bean.code,callback:function(t){e.$set(e.bean,"code",t)},expression:"bean.code"}})],1)],1)],1),t("el-row",[t("el-col",{attrs:{span:24}},[t("el-form-item",{attrs:{label:"内容:",prop:"value"}},[t("el-input",{attrs:{size:"mini","prefix-icon":"el-icon-edit",placeholder:"请输入内容"},model:{value:e.bean.value,callback:function(t){e.$set(e.bean,"value",t)},expression:"bean.value"}})],1)],1)],1),t("el-row",[t("el-col",{attrs:{span:24}},[t("el-form-item",{attrs:{label:"顺序:",prop:"sortOrder"}},[t("el-input",{attrs:{size:"mini","prefix-icon":"el-icon-edit",placeholder:"请输入排序序号（升序）"},model:{value:e.bean.sortOrder,callback:function(t){e.$set(e.bean,"sortOrder",t)},expression:"bean.sortOrder"}})],1)],1)],1),t("el-row",[t("el-col",{attrs:{span:24}},[t("el-form-item",{attrs:{label:"类型:",prop:"type"}},[t("el-input",{attrs:{size:"mini","prefix-icon":"el-icon-edit",placeholder:"请输入类型"},model:{value:e.bean.type,callback:function(t){e.$set(e.bean,"type",t)},expression:"bean.type"}})],1)],1)],1),t("el-row",[t("el-col",{attrs:{span:24}},[t("el-form-item",{attrs:{label:"备注:",prop:"description"}},[t("el-input",{attrs:{size:"mini","prefix-icon":"el-icon-edit",type:"textarea",rows:"3",placeholder:"请输入备注内容"},model:{value:e.bean.description,callback:function(t){e.$set(e.bean,"description",t)},expression:"bean.description"}})],1)],1)],1),t("el-input",{attrs:{type:"hidden",id:"sys"}})],1)],1),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:e.doAddBean}},[e._v("确 定")])],1)])],1)])},l=[],a={name:"SysDatabaseType",data:function(){var e=this,t=function(t,n,i){if(n)return e.checkUniqueByType(0,n,i);i(new Error("名称不能为空"))},n=function(t,n,i){if(n)return e.checkUniqueByType(1,n,i);i(new Error("编码不能为空"))};return{total:0,page:1,size:this.$ELEMENT.pagesize,keyword:"",loading:!1,beanlist:[],bean:{id:null,code:"",name:"",value:"",description:"",sortOrder:0,type:"",sys:0},title:"添加基础数据",dialogVisible:!1,rules:{name:[{validator:t,trigger:"blur",required:!0}],code:[{validator:n,trigger:"blur",required:!0}],value:[{required:!0,message:"请输入内容",trigger:"blur"}]}}},mounted:function(){this.initBeanlist()},methods:{checkUniqueByType:function(e,t,n){var i="/system/databasetype/checkUnique?value="+t+"&type="+e+"&id="+this.bean.id;this.getRequest(i).then((function(t){if(t)return n();var i=0==e?"名称不能重复":"编码不能重复";return n(new Error(i))}))},formatterText:function(e,t){var n=t.property,i=e[n];if("sys"==n)return"0"==i?"否":"是"},showAddBeanView:function(){this.emptyBean(),this.title="添加基础数据",this.dialogVisible=!0},showEditBeanView:function(e){this.title="编辑基础数据",this.bean=e,this.dialogVisible=!0},sizeChange:function(e){this.size=e,this.initBeanlist()},currentChange:function(e){this.page=e,this.initBeanlist()},initBeanlist:function(){var e=this;this.loading=!0;var t="/system/databasetype/?page="+this.page+"&size="+this.size;t+="&keyword="+this.keyword,this.getRequest(t).then((function(t){e.loading=!1,t&&(e.beanlist=t.data,e.total=t.total)}))},emptyBean:function(){this.bean={id:null,code:"",name:"",value:"",description:"",sortOrder:0,type:"",sys:0}},deleteBean:function(e){var t=this;1!=e.sys?this.$confirm("此操作将永久删除【"+e.name+"】, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){t.deleteRequest("/system/databasetype/"+e.id).then((function(e){e&&t.initBeanlist()}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})})):this.$alert("该数据为系统设置，不允许删除")},doAddBean:function(){var e=this;this.bean.id?this.$refs["beanForm"].validate((function(t){t&&e.putRequest("/system/databasetype/",e.bean).then((function(t){t&&(e.dialogVisible=!1,e.initBeanlist())}))})):this.$refs["beanForm"].validate((function(t){t&&e.postRequest("/system/databasetype/",e.bean).then((function(t){t&&(e.dialogVisible=!1,e.initBeanlist())}))}))}}},s=a,o=n("2877"),r=Object(o["a"])(s,i,l,!1,null,"3fb719b5",null);t["default"]=r.exports},9747:function(e,t,n){},d1e3:function(e,t,n){"use strict";n.r(t);var i=function(){var e=this,t=e._self._c;return t("div",[e._v("\n    备份恢复数据库\n")])},l=[],a={name:"SysData"},s=a,o=n("2877"),r=Object(o["a"])(s,i,l,!1,null,"8fff1cc0",null);t["default"]=r.exports},eeb1:function(e,t,n){},f61b:function(e,t,n){}}]);
//# sourceMappingURL=chunk-ae543f58.a27bd7ae.js.map