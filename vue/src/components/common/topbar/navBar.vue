<template>
	<div class="nav-bar-wrapper">
		<div class="nav-bar">
			<ul>
				<li>
					<a href="#" class="active">
						<i class="fas fa-list-ul"></i>
						<span>发现</span>
					</a>
				</li>
				<li>
					<a href="#">
						<i class="fas fa-share"></i>
						<span>分享</span>
					</a>
				</li>
				<li>
					<a href="#">
						<i class="fas fa-newspaper"></i>
						<span>文章</span>
					</a>
				</li>
				<li>
					<a href="#">
						<i class="fas fa-file-text"></i>
						<span>活动</span>
					</a>
				</li>
				<li>
					<a href="#">
						<i class="fas fa-download"></i>
						<span>下载</span>
					</a>
				</li>
			</ul>
		</div>
	</div>
</template>

<script>
export default {
	name: "navBar",
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
