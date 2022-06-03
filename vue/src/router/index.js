import Vue from 'vue'
import Router from 'vue-router'
import Main from '../views/Main'
import Login from '../views/Login'
import NotFound from '../views/NotFound'
import BoardList from "../views/BoardList"
import NewTopics from "../views/NewTopics"
import Focus from "../views/focus/Focus"
import Board from "../views/focus/Board";
import User from "../views/focus/User";
import Favorite from "../views/focus/Favorite";
import Message from "../views/Message";
import Response from "../views/message/Response";
import Attme from "../views/message/Attme";
import System from "../views/message/System";
import DirectMessages from "../views/message/DirectMessages";
import UserCenter from "../views/usercenter/UserCenter";
import Detail from "../views/usercenter/Detail";
import Config from "../views/usercenter/Config";
import MyTopics from "../views/usercenter/MyTopics";
import MyPosts from "../views/usercenter/MyPosts";
import MyFavorites from "../views/usercenter/MyFavorites";
import MyFollowings from "../views/usercenter/MyFollowings";
import MyFans from "../views/usercenter/MyFans";
import PostTopic from "../views/PostTopic";
import TopicDetail from '../views/TopicDetail'
import BoardDetail from '../views/BoardDetail'
import MyFocusBoards from '../views/usercenter/MyFocusBoards'

Vue.use(Router)
const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'main',
      component: Main
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/boardList',
      name: 'boardList',
      component: BoardList
    },
    {
      path: '/newTopics',
      name: 'newTopics',
      component: NewTopics
    },
    {
      path: '/focus',
      name: 'focus',
      component: Focus,
      children: [
        {
          path: '/focus/board',
          name: 'board',
          component: Board,
          alias: ''
        },
        {
          path: '/focus/user',
          name: 'user',
          component: User
        },
        {
          path: '/focus/favorite',
          name: 'favorite',
          component: Favorite
        }
      ]
    },
    {
      path: '/message',
      name: 'message',
      component: Message,
      children: [
        {
          path: '/message/response',
          name: 'response',
          component: Response
        },
        {
          path: '/message/attme',
          name: 'attme',
          component: Attme
        },
        {
          path: '/message/system',
          name: 'system',
          component: System
        },
        {
          path: '/message/directMessages',
          name: 'directMessages',
          component: DirectMessages
        }
      ]
    },
    {
      path: '/usercenter',
      name: 'usercenter',
      component: UserCenter,
      children: [
        {
          path: '/usercenter/detail',
          name: 'detail',
          component: Detail
        },
        {
          path: '/usercenter/config',
          name: 'config',
          component: Config
        },
        {
          path: '/usercenter/mytopics',
          name: 'mytopics',
          component: MyTopics
        },
        {
          path: '/usercenter/myposts',
          name: 'myposts',
          component: MyPosts
        },
        {
          path: '/usercenter/myfavorites',
          name: 'myfavorites',
          component: MyFavorites
        },
        {
          path: '/usercenter/myfocusboards',
          name: 'myfocusboards',
          component: MyFocusBoards
        },
        {
          path: '/usercenter/myfollowings',
          name: 'myfollowings',
          component: MyFollowings
        },
        {
          path: '/usercenter/myfans',
          name: 'myfans',
          component: MyFans
        }
      ]
    },
    {
      path: '/postTopic/:id',
      name: 'PostTopic',
      component: PostTopic
    },
    {
      path: '/topic/:id',
      name: 'TopicDetail',
      component: TopicDetail
    },
    {
      path: '/board/:id',
      name: 'BoardDetail',
      component: BoardDetail
    },
    {
      path: '/gohome',
      redirect: '/main'
    },
    {
      path: '*',
      component: NotFound
    }
  ]
})
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
  if (to.path === '/register' || to.path === '/login') {
    next()
  } else {
    let token = localStorage.getItem('token')
    if (token === null || token === '') {
      next('/login')
    } else {
      next()
    }
  }
})
const VueRouterPush = Router.prototype.push
Router.prototype.push = function push(to) {
  return VueRouterPush.call(this, to).catch(err => err)
}

export default router

