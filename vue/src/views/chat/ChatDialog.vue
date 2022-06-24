<template>
  <div class="container">
    <div class="dialog-other" v-show="item.type === 1">
      <div class="other-time">
        {{ item.gmtCreate | formatDate }}
      </div>
      <div class="other-content">
        <div class="other-content-avatar">
          <img :src=item.other.avatarUrl alt="">
        </div>
        <div class="other-content-text">
          <span>{{ item.content }}</span>
        </div>
      </div>
    </div>
    <div class="dialog-me" v-show="item.type === 2">
      <div class="me-time">
        {{ item.gmtCreate | formatDate }}
      </div>
      <div class="me-content">
        <div class="me-content-avatar">
          <img :src=item.me.avatarUrl alt="">
        </div>
        <div class="me-content-text">
          <span>{{ item.content }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ChatDialog",
  props: ['item'], filters: {
    formatDate: function (value) {
      let date = new Date(value)
      let y = date.getFullYear()
      let MM = date.getMonth() + 1
      MM = MM < 10 ? ('0' + MM) : MM
      let d = date.getDate()
      d = d < 10 ? ('0' + d) : d
      let h = date.getHours()
      h = h < 10 ? ('0' + h) : h
      let m = date.getMinutes()
      m = m < 10 ? ('0' + m) : m
      let s = date.getSeconds()
      s = s < 10 ? ('0' + s) : s
      return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s
    }
  }
}
</script>

<style scoped>
/*
* other style
*/
.dialog-other {
  display: flex;
  flex-direction: column;
}

.other-time {
  margin: 5px auto;
  background: rgba(209, 227, 223, 0.5);
  color: #FFFFFF;
  border-radius: 2px;
  font-size: 4px;
  padding: 3px;
}

.other-content {
  display: flex;
  flex-direction: row;
}

.other-content-avatar {
  margin-left: 10px;
  width: 35px;
  height: 35px;
}

.other-content-avatar img {
  width: 100%;
  height: 100%;
  border-radius: 5%;
}

.other-content-text {
  position: relative;
  margin-top: 5px;
  margin-left: 10px;
  border-radius: 2px;
  min-width: 20px;
  max-width: 220px;
  height: 100%;
  min-height: 30px;
  background-color: #4494D5;
  color: #FFFFFF;
  font-size: 14px;
  line-height: 20px;
  word-break: break-all;
}

.other-content-text span {
  display: inline-block;
  margin: 5px;
}

.other-content-text:before {
  position: absolute;
  left: -24px;
  top: 5px;
  content: '';
  border-top: 5px solid transparent;
  border-bottom: 5px solid transparent;
  border-left: 12px solid transparent;
  border-right: 12px solid #4494D5;
}

/*
* me style
*/
.dialog-me {
  display: flex;
  flex-direction: column;
}

.me-time {
  margin: 5px auto;
  background: rgba(209, 227, 223, 0.5);
  color: #FFFFFF;
  border-radius: 2px;
  font-size: 4px;
  padding: 3px;
}

.me-content {
  display: flex;
  flex-direction: row-reverse;
}

.me-content-avatar {
  margin-right: 10px;
  width: 35px;
  height: 35px;
}

.me-content-avatar img {
  width: 100%;
  height: 100%;
  border-radius: 5%;
}

.me-content-text {
  display: flex;
  flex-direction: row-reverse;
  position: relative;
  margin-top: 5px;
  margin-right: 10px;
  border-radius: 2px;
  min-width: 20px;
  max-width: 220px;
  height: 100%;
  min-height: 30px;
  background-color: #B0E36F;
  color: #FFFFFF;
  font-size: 14px;
  line-height: 20px;
  word-break: break-all;
}

.me-content-text span {
  display: inline-block;
  margin: 5px;
}

.me-content-text:after {
  position: absolute;
  right: -23px;
  top: 5px;
  content: '';
  border-top: 5px solid transparent;
  border-bottom: 5px solid transparent;
  border-right: 12px solid transparent;
  border-left: 12px solid #B0E36F;
}
</style>
