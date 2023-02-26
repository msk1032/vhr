<template>
  <div>
    <div>
      <el-input
        size="small"
        v-model="jl.name"
        style="width: 300px"
        prefix-icon="el-icon-plus"
        placeholder="添加职称..."
      ></el-input>
      <el-select
        v-model="jl.titleLevel"
        placeholder="职称等级"
        size="small"
        style="margin-left: 5px; margin-right: 5px"
      >
        <el-option
          v-for="item in titleLevels"
          :key="item"
          :label="item"
          :value="item"
        >
        </el-option>
      </el-select>
      <el-button
        icon="el-icon-plus"
        type="primary"
        size="small"
        @click="addJobLevel"
        >添加</el-button
      >
    </div>
    <div>
      <el-table
        :data="jls"
        border
        stripe
        size="small"
        @selection-change="handleSelectionChange"
        style="width: 70%; margin-top: 10px"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="id" label="编号" width="55"> </el-table-column>
        <el-table-column prop="name" label="职称名称" width="150">
        </el-table-column>
        <el-table-column prop="titleLevel" label="职称级别" width="150">
        </el-table-column>
        <el-table-column prop="createDate" label="创建时间" width="150">
        </el-table-column>
        <!-- <el-table-column label="是否启用">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.enabled">已启用</el-tag>
            <el-tag type="danger" v-else>未启用</el-tag>
          </template>
        </el-table-column> -->
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="small" @click="showEditView(scope.row)"
              >编辑</el-button
            >
            <el-button
              size="small"
              type="danger"
              @click="deleteHandler(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-button
        type="danger"
        size="small"
        style="margin-top: 10px"
        :disabled="multipleSelection.length == 0"
        @click="deleteMany"
        >批量删除
      </el-button>
    </div>

    <el-dialog title="修改职称" :visible.sync="dialogVisible" width="30%">
      <div>
        <table>
          <tr>
            <td>
              <el-tag>职称名</el-tag>
            </td>
            <td>
              <el-input size="small" v-model="updateJl.name"></el-input>
            </td>
          </tr>
          <tr>
            <td>
              <el-tag>职称级别</el-tag>
            </td>
            <td>
              <el-select
                v-model="updateJl.titleLevel"
                placeholder="职称等级"
                size="small"
                style="margin-left: 5px; margin-right: 5px"
              >
                <el-option
                  v-for="item in titleLevels"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </td>
          </tr>
          <tr>
            <td>
              <el-tag>是否启用</el-tag>
            </td>
            <td>
              <el-switch
                v-model="updateJl.enabled"
                active-text="启用"
                inactive-text="禁用"
              >
              </el-switch>
            </td>
          </tr>
        </table>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="doUpdate"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible:false,
      multipleSelection: [],
      jl: {
        name: "",
        titleLevel: "",
      },
      updateJl: {
        name: "",
        titleLevel: "",
      },
      jls: [],
      titleLevels: ["正高级", "副高级", "中级", "初级", "员级"],
    };
  },
  mounted() {
    this.initJLs();
  },

  methods: {
    initJLs() {
      this.getRequest("/system/basic/joblevel/").then((res) => {
        if (res) {
          console.log(res.obj);
          this.jls = res.obj;
        }
      });
    },

    addJobLevel() {
      this.postRequest("/system/basic/joblevel/", this.jl).then((res) => {
        if (res) {
          this.jl.name = "";
          this.jl.titleLevel = "";
          this.initJLs();
        }
      });
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      //console.log(val);
      for (let index = 0; index < val.length; index++) {
        this.multipleSelection[index] = val[index].id;
      }
      //console.log(this.multipleSelection);
    },
    showEditView(row) {
      this.dialogVisible = true;
      this.updateJl = row
    },
    doUpdate() {
      this.putRequest("/system/basic/joblevel/", this.updateJl).then(res=>{
        if(res) {
          this.dialogVisible=false
          this.updateJl.name = ""
          this.updateJl.titleLevel=""
          this.initJLs()
        }
      })
    },
    deleteHandler(row) {
      //console.log(row.id)
      this.$confirm(
        "此操作将永久删除职称[" + row.name + "], 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          this.deleteRequest("/system/basic/joblevel/" + row.id).then((res) => {
            if (res) {
              this.initJLs();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消操作",
          });
        });
    },
    deleteMany() {
      this.$confirm(
        "此操作将永久删除" +
          this.multipleSelection.length +
          "条记录, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          this.postRequest(
            "/system/basic/joblevel/many",
            this.multipleSelection
          ).then((res) => {
            if (res) {
              this.initJLs();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消操作",
          });
        });
    },
  },
};
</script>

<style>
</style>