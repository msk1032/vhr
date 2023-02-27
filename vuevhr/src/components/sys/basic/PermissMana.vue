<template>
  <div>
    <div class="permissManaTool">
      <el-input size="small" placeholder="请输入角色英文名" v-model="role.name">
        <template slot="prepend">ROLE_</template>
      </el-input>
      <el-input
        size="small"
        placeholder="请输入角色中文名"
        v-model="role.nameZh"
        @keydown.enter.native="doAddRole"
      ></el-input>
      <el-button
        type="primary"
        size="small"
        icon="el-icon-plus"
        @click="doAddRole"
        >添加角色</el-button
      >
    </div>
    <div >
      <el-collapse
        v-model="activeName"
        accordion
        @change="change"
      >
        <el-collapse-item
          :title="r.nameZh"
          :name="r.id"
          v-for="(r, index) in roles"
          :key="index"
        >
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>可访问的资源</span>
              <el-button
                style="float: right; padding: 3px 0; color: #ff0000"
                icon="el-icon-delete"
                type="text"
                @click=""
              ></el-button>
            </div>
            <div>
              <el-tree
                show-checkbox
                node-key="id"
                ref="tree"
                :key="index"
                :default-checked-keys="selectedMenus"
                :data="allmenus"
                :props="defaultProps"
              ></el-tree>
              <div style="display: flex; justify-content: flex-end">
                <el-button @click="cancelUpdate">取消修改</el-button>
                <el-button type="primary" @click="doUpdate(r.id,index)">确认修改</el-button
                >
              </div>
            </div>
          </el-card> 
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeName:"",
      role: {
        name: "",
        nameZh: "",
      },
      roles:[],
      allmenus:[],
      selectedMenus:[],
      defaultProps:{
        children:'children',
        label:'name'
      }
    };
  },
  mounted() {
    this.initRoles()
  },

  methods: {
    doAddRole() {
      let perfix = "ROLE_"
      perfix +=this.role.name
      this.role.name = perfix
      console.log(perfix)
      this.postRequest("/system/basic/permiss/", this.role).then(res =>{
        if(res) {
          this.role.name = ""
          this.role.nameZh = ""
          this.initRoles();
        }
      })
    },
    initRoles() {
      this.getRequest("/system/basic/permiss/").then(res=> {
        if(res){
          console.log(res.obj)
          this.roles = res.obj
        }
      }) 
    },

    change(rid) {
      //alert(rid)
      if(rid){
        this.initAllMenus()
        console.log(rid)
        this.initSelectMenus(rid)
      }
    },

    initAllMenus() {
      this.getRequest("/system/basic/permiss/menus").then(res=>{
        if(res){
          this.allmenus = res.obj;
        }
      })
    },

    initSelectMenus(rid) {
      this.getRequest("/system/basic/permiss/menus/"+rid).then(res=>{
        if(res){
          this.selectedMenus = res.obj;
        }
      })
    },
    cancelUpdate() {

    },

    doUpdate(id,index){
      this.$refs.tree
      let tree = this.$refs.tree[index]
      let selectedKeys = tree.getCheckedKeys(true)
      this.putRequest("/system/basic/permiss/update/"+id, selectedKeys).then(res=>{
        if(res) {
          this.activeName = '';
        }
      })

    },











  },
};
</script>

<style>
.permissManaTool {
  display: flex;
  justify-content: flex-start;
}

.permissManaTool .el-input {
  width: 300px;
  margin-right: 6px;
}

.permissManaMain {
  margin-top: 10px;
  width: 700px;
}
</style>