<template>
  <div>
    <!--    <breadcrumb></breadcrumb>-->
    <router-view></router-view>
  </div>
</template>

<script>
import Breadcrum from "./Breadcrumb"

export default {
  name: 'Main',
  components: {
    breadcrumb: Breadcrum
  },
  beforeRouteEnter: (to, from, next) => {
    console.log('---before')
    next(vm => {
      vm.getData()
    })
  },
  beforeRouteLeave: (to, from, next) => {
    console.log('---hou')
    next()
  },
  methods: {
    getData: function () {
      this.axios({
        method: 'get',
        url: '/index'
      }).then(function (res) {
        console.log(res.data)
      })
    }
  }

}
</script>

<style scoped>

</style>
