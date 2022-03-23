<template>
	<div class="top-bar-wrapper">
		<div class="top-bar">
			<!-- logo图标-->
			<div class="logo">
				<a href="#">
					<img src="@/assets/img/logo.svg" alt="" />
				</a>
			</div>
			<!--搜索栏-->
			<div class="search">
				<form action="#" class="navbar-search">
					<input type="text" placeholder="搜索问题、话题或人" />
					<i class="fas fa-search"></i>
				</form>
			</div>
			<!--导航韩-->
			<nav-bar></nav-bar>
			<div class="login-wrapper">
				<a href="#" class="login">登录</a>
			</div>
		</div>
	</div>
</template>
<script>
import navBar from "@/components/common/topbar/navBar";
export default {
	name: "topBar",
	components: {
		navBar,
	},
	setup() {},
};
</script>

<style lang="less">
@import "@/assets/fa/css/all.css";
@top-height: 52px;
.top-bar-wrapper {
	width: 100%;
	height: @top-height;
	line-height: @top-height;
	background-color: #499ef3;
	.top-bar {
		display: flex;
		width: 1170px;
		line-height: @top-height;
		height: @top-height;
		margin: 0 auto;
		position: relative;
		align-items: center;
		.logo {
			width: 90px;
			height: 40px;

			a {
				display: block;
				width: 90px;
				height: 40px;
				img {
					width: 80px;
					height: 40px;
				}
			}
		}
	}
	.search {
		width: 230px;
		height: 32px;
		margin: 10px 0;
		position: relative;
		.navbar-search {
			margin: 0;

			input {
				width: 194px;
				height: 20px;
				padding: 6px 30px 6px 6px;
				resize: none;
				box-shadow: none;
				display: block;
				font-size: 14px;
				color: #555;
				border-radius: 4px;
				background-color: #fff;
				background-image: none;
				border: none;
				&:focus {
					outline: none;
				}
			}
			i {
				color: #999;
				position: absolute;
				right: 5px;
				top: 6px;
				font-size: 18px;
			}
		}
	}
	.login-wrapper {
		position: absolute;
		right: 10px;
		.login {
			background-color: #59b0ff;
			text-decoration: none;
			border-radius: 4px;
			padding: 6px 12px;
			font-size: 14px;
			color: #fff;
		}
	}
}
</style>
