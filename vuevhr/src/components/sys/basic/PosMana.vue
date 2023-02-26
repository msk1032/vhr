<template>
  <div>
    <div>
      <el-input
        size="small"
        class="addPosInput"
        placeholder="添加职位..."
        prefix-icon="el-icon-plus"
        v-model="pos.name"
        @keydown.enter.native="addPosition"
      >
      </el-input>
      <el-button
        icon="el-icon-plus"
        size="small"
        type="primary"
        @click="addPosition"
        >添加</el-button
      >
    </div>
    <div class="posManaMain">
      <el-table
        :data="positions"
        border
        stripe=""
        style="width: 70%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="id" label="编号" width="55"> </el-table-column>
        <el-table-column prop="name" label="职位名称" width="180">
        </el-table-column>
        <el-table-column prop="createDate" label="创建时间" width="200">
        </el-table-column>

        <el-table-column label="是否启用">
          <template slot-scope="scope">
            <el-tag size="small" type="success" v-if="scope.row.enabled"
              >已启用</el-tag
            >
            <el-tag size="small" type="danger" v-else>未启用</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="showEditView(scope.row)"
              >编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.row)"
              >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-dialog title="修改职位" :visible.sync="dialogVisible" width="30%">
      <div>
        <div>
          <el-tag>职位名称</el-tag>
          <el-input
            class="updatePosInput"
            size="small"
            v-model="updatePos.name"
          ></el-input>
        </div>
        <div>
          <el-tag>是否启用</el-tag>
          <el-switch
            v-model="updatePos.enabled"
            active-text="启用"
            inactive-text="禁用"
          >
          </el-switch>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="doUpdate"
          >确 定</el-button
        >
      </span>
    </el-dialog>

    <el-button
      @click="deleteMany"
      type="danger"
      size="small"
      style="margin-top: 8px"
      :disabled="multipleSelection.length==0"
      >批量删除
    </el-button>
  </div>
</template>

<script>
//import { putRequest } from "@/utils/api";
export default {
  data() {
    return {
      dialogVisible: false,

      pos: {
        name: "",
      },
      updatePos: {
        name: "",
        enabled: false,
      },

      positions: [],
      multipleSelection: [],
    };
  },
  mounted() {
    this.initPositions();
  },

  methods: {
    deleteMany() {
      this.$confirm("此操作将永久删除"+this.multipleSelection.length+"条记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          console.log(this.multipleSelection)
          this.postRequest("/system/basic/pos/many", this.multipleSelection).then(
            res => {
              if (res) {
                console.log(res.msg);
                this.initPositions();
              }
            }
          );
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消操作",
          });
        });
    },

    doUpdate() {
      this.putRequest("/system/basic/pos/", this.updatePos).then((res) => {
        if (res) {
          this.initPositions();
          this.updatePos.name = "";
          this.dialogVisible = false;
        }
      });
    },

    showEditView(row) {
      this.dialogVisible = true;
      this.updatePos = row;
    },

    handleDelete(row) {
      //console.log(row.id);
      this.$confirm("此操作将永久删除职位" + row.name + ", 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.deleteRequest("/system/basic/pos/" + row.id).then((res) => {
            console.log("/system/basic/pos/" + row.id);
            if (res) {
              this.initPositions();
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
    initPositions() {
      this.getRequest("/system/basic/pos/").then((res) => {
        //console.log(res);
        this.positions = res.obj;
      });
    },

    handleSelectionChange(val) {
      this.multipleSelection = val
      console.log(val);
      for (let index = 0; index < val.length; index++) {
        this.multipleSelection[index] = val[index].id;
      }
      console.log(this.multipleSelection);
    },

    addPosition() {
      if (this.pos.name) {
        this.postRequest("/system/basic/pos/", this.pos).then((res) => {
          if (res) {
            this.initPositions();
            this.pos.name = "";
          }
        });
      } else {
        this.$message.error({ message: "please input position name" });
      }
    },
  },
};
</script>

<style>
.addPosInput {
  width: 300px;
  margin-right: 8px;
}

.updatePosInput {
  width: 200px;
  margin-left: 8px;
}

.posManaMain {
  margin-top: 10px;
}
</style>