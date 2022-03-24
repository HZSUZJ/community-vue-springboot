import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
const Home =() => import('@/views/home/Home.vue')
const Article =() => import('@/views/article/Article.vue')
const Download =() => import('@/views/download/Download.vue')
const Event =() => import('@/views/event/Event.vue')
const Slides =() => import('@/views/slides/Slides.vue')


const routes: Array<RouteRecordRaw> = [
  {
    path:'/',
    redirect: '/home'
  },
  {
    path:'/home',
    name:'/home',
    component:Home
  },
  {
    path:'/article',
    name:'/article',
    component:Article
  },
  {
    path:'/download',
    name:'download',
    component:Download
  },
  {
    path:'/event',
    name:'event',
    component:Event
  },
  {
    path:'/slides',
    name:'/slides',
    component:Slides
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
