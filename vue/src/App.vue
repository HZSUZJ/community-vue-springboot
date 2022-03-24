<template>
	<top-bar></top-bar>
	<router-view #="{ Component }">
		<keep-alive>
			<component :is="Component"></component>
		</keep-alive>
	</router-view>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import topBar from "@/components/common/topbar/topBar.vue";

export default defineComponent({
	name: "App",
	components: {
		topBar,
	},
	setup() {},
});
</script>
<style lang="less" scoped>
@import "@/assets/css/reset.css";
</style>
