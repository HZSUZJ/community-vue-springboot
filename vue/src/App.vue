<template>
    <div id="app">
        <el-container>
            <el-header>
                <nav-menu></nav-menu>
            </el-header>
        </el-container>
        <router-view />
    </div>
</template>

<script>
import navMenu from "./components/NavMenu"


export default {
    name: 'App',
    components: {
        navMenu: navMenu
    }
}
</script>

<style>
#app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
}
</style>
