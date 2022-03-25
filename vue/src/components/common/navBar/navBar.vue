<template>
	<div class="nav-bar-wrapper">
		<div class="nav-bar">
			<ul>
				<li>
					<nav-bar-item
						itemName="发现"
						icon="fa-list-ul"
						itemPath="/home"
					></nav-bar-item>
				</li>
				<li>
					<nav-bar-item
						itemName="分享"
						icon="fa-share"
						itemPath="/slides"
					></nav-bar-item>
				</li>
				<li>
					<nav-bar-item
						itemName="文章"
						icon="fa-newspaper"
						itemPath="/article"
					></nav-bar-item>
				</li>
				<li>
					<nav-bar-item
						itemName="活动"
						icon="fa-file-text"
						itemPath="/event"
					></nav-bar-item>
				</li>
				<li>
					<nav-bar-item
						itemName="下载"
						icon="fa-download"
						itemPath="/download"
					></nav-bar-item>
				</li>
			</ul>
		</div>
	</div>
</template>

<script>
import navBarItem from "@/components/common/navBar/navBarItem";
export default {
	name: "navBar",
	components: {
		navBarItem,
	},
};
</script>

<style lang="less">
@import "@/assets/fa/css/all.css";
@hover-color: #4dabff;
.nav-bar-wrapper {
	margin-left: 15px;
	.nav-bar {
		ul {
			display: flex;
			li {
				display: block;
				a {
					text-decoration: none;
					color: #fff;
					padding: 16px;
					font-size: 14px;
					i {
						margin-right: 3px;
					}
					&:hover {
						background: @hover-color;
					}
				}
				.active {
					background: @hover-color;
					font-weight: bold;
				}
			}
		}
	}
}
</style>
