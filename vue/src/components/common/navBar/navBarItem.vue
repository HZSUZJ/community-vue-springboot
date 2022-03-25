<template>
	<a @click="itemClick" :class="{ active: isActive }">
		<i class="fas" :class="icon"></i>
		<span>{{ itemName }}</span>
	</a>
</template>

<script>
import { ref, computed, watch } from "vue";
import { useRouter } from "vue-router";
export default {
	name: "navBarItem",
	props: {
		itemName: String,
		icon: String,
		itemPath: String,
	},
	setup(props) {
		const router = useRouter();

		// console.log(this.$route);
		const itemClick = () => {
			router.push({ path: props.itemPath });
		};
		let isActive = computed(() => {
			return router.currentRoute.value.fullPath.indexOf(props.itemPath) !== -1;
		});
		return { isActive, itemClick };
	},
};
</script>

<style lang="less"></style>
