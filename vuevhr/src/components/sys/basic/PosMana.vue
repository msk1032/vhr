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
      <el-table :data="positions" border stripe="" style="width: 70%" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="id" label="编号" width="55"> </el-table-column>
        <el-table-column prop="name" label="职位名称" width="180">
        </el-table-column>
        <el-table-column prop="createDate" label="创建时间" width="200">
        </el-table-column>

        <!-- <el-table-column label="是否启用">
          <template slot-scope="scope">
            <el-tag size="small" type="success" v-if="scope.row.enabled"
              >已启用</el-tag
            >
            <el-tag size="small" type="danger" v-else>未启用</el-tag>
          </template>
        </el-table-column>-->
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="showEditView(scope.$index, scope.row)"
              >编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除
            </el-button>
          </template>
        </el-table-column> 
      </el-table>
    </div>
  </div>
</template>

<script>
import { postKeyValueRequest } from "@/utils/api";
export default {
  data() {
    return {
      pos: {
        name: "",
      },

      positions: [],
      multipleSelection :[],
    };
  },
  mounted() {
    this.initPositions();

  },

  methods: {
    initPositions() {
      this.getRequest("/system/basic/pos/").then((res) => {
        console.log(res);
        this.positions = res.obj;
      });
    },

    handleSelectionChange(val) {
        console.log(val)
        for (let index = 0; index < val.length; index++) {
            this.multipleSelection[index] = val[index].id
            
        }
        console.log(this.multipleSelection)

    },

    addPosition() {
      postKeyValueRequest().then((res) => {});
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