<template>
  <top-bar></top-bar>
</template>
<script lang="ts">
import { defineComponent } from 'vue'
import topBar from '@/components/common/topbar/topBar.vue'

export default defineComponent({
  name:'App',
  components:{
    topBar
  },
  setup(){

  }
})
</script>
<style lang="less">
*{
  margin: 0;
  padding: 0;
}
</style>