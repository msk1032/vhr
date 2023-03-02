<template>
  <div style="width: 500px">
    <el-input
      placeholder="请输入部门名称进行搜索..."
      prefix-icon="el-icon-search"
      v-model="filterText"
    >
    </el-input>

    <el-tree
      :data="deps"
      :props="defaultProps"
      :expand-on-click-node="false"
      :filter-node-method="filterNode"
      ref="tree"
    >
      <span
        class="custom-tree-node"
        style="display: flex; justify-content: space-between; width: 100%"
        slot-scope="{ node, data }"
      >
        <span>{{ data.name }}</span>
        <span>
          <el-button
            type="primary"
            size="mini"
            class="depBtn"
            @click="() => showAddDepView(data)"
          >
            添加部门
          </el-button>
          <el-button
            type="danger"
            size="mini"
            class="depBtn"
            @click="() => deleteDep(data)"
          >
            删除部门
          </el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog title="添加部门" :visible.sync="dialogVisible" width="30%">
      <div>
        <table>
          <tr>
            <td>
              <el-tag>上级部门</el-tag>
            </td>
            <td>{{ pname }}</td>
          </tr>
          <tr>
            <td>
              <el-tag>部门名称</el-tag>
            </td>
            <td>
              <el-input
                v-model="dep.name"
                placeholder="请输入部门名称..."
              ></el-input>
            </td>
          </tr>
        </table>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="doAddDep">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      filterText: "",
      deps: [],
      defaultProps: {
        children: "children",
        label: "name",
      },
      dialogVisible:false,
      pname: "",
      dep:{
        name:"",
        parentId: ""
      },
      delList: []
    };
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true;
      return data.name.indexOf(value) !== -1;
    },

    initDeps() {
      this.getRequest("/system/basic/department/").then((res) => {
        if (res) {
          this.deps = res.obj;
        }
      });
    },

    showAddDepView(data) {
      console.log(data);
      this.dialogVisible = true
      this.pname = data.name
      this.dep.parentId = data.id
    },
    addDeptoDeps(dep, deps) {
      for (let i = 0; i < deps.length; i++) {
        let d = deps[i];
        if(d.id == dep.parentId){
          d.children = d.children.concat(dep);
          return
        }
        else{
          this.addDeptoDeps(dep, d.children)
        }
        
      }
    },

    doAddDep(){
      this.putRequest("/system/basic/department/", this.dep).then(res=>{
        if(res){
          this.dialogVisible = false;
          this.dep.name=""
          this.addDeptoDeps(res.obj, this.deps);

        }
      })
    },
    getDelList(data) {
      if(data.parent) {
        this.delList.push(data.id);
        let children = data.children;
        for(let i = 0; i < children.length; i++ ){
          if(children[i].parent){
            this.getDelList(children[i]);
          }else{
            this.delList.push(children[i].id)
          }
        }
      }

    },

    deleteDep(data) {
      this.delList = [];
      //console.log(data);
      this.getDelList(data)
      console.log(this.delList)
      this.$confirm(
        "此操作将永久删除[" + data.name + "]角色, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          this.postRequest("/system/basic/department/delete", this.delList).then((res) => {
            if (res) {
              this.initDeps();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },

  mounted() {
    this.initDeps();
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    },
  },
};
</script>

<style>
.depBtn {
  padding: 2px;
}
</style>