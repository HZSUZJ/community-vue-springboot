<template>
	<a :href="path" @click="itemClick" :class="{ active: isActive }">
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
		const fullPath = router.currentRoute.value.fullPath;
		console.log(router);
		let isActive = computed(() => {
			// return route.path.indexOf(this.path) !== -1;
		});
		// console.log(this.$route);
		const itemClick = () => {
			router.push({ name: props.itemPath });
		};

		return { isActive, itemClick };
	},
};
</script>

<style lang="less"></style>
