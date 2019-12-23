import Vue from 'vue'
import Router from 'vue-router'
import auth from './auth'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import Organizations from './views/Organizations.vue'
import Leagues from './views/Leagues.vue'
import Teams from './views/Teams.vue'
import Register from './views/Register.vue'
import Availabilities from './views/Availabilities.vue'
import Users from './views/Users.vue'
import Admin from './views/Admin.vue'
// import AdminRoute from './views/AdminRoute'
import Unauthorized from './views/Unauthorized'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/admin',
      name: 'admin',
      component: Admin,
      meta: {
        requiresAuth: true
      }
    },
    // {
    //   path: '/adminRoute',
    //   name: 'adminroute',
    //   component: AdminRoute,
    //   meta: {
    //     requiresAuth: true
    //   }
    // },
    {
      path: '/unauthorized',
      name: 'unauthorized',
      component: Unauthorized,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/organization/list",
      name: "organization-list",
      component: Organizations,
      meta: {
        requiresAuth: true
      }
    },  
    {
      path: "/organization/:orgId/edit",
      name: "organization-edit",
      component: Organizations,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/organization/new",
      name: "organization-new",
      component: Organizations,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/organization/:orgId/league/list",
      name: "league-list",
      component: Leagues,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/organization/:orgId/league/new",
      name: "league-new",
      component: Leagues,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/organization/:orgId/league/:leagueId/edit",
      name: "league-edit",
      component: Leagues,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/league/:leagueId/team/list",
      name: "team-list",
      component: Teams,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/league/:leagueId/team/new",
      name: "team-new",
      component: Teams,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/league/:leagueId/team/:teamId/edit",
      name: "team-edit",
      component: Teams,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/team/:teamId/teamprofile",
      name: "availability-list",
      component: Availabilities,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/team/:teamId/availability/new",
      name: "availability-new",
      component: Availabilities,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/team/:teamId/availability/:availabilityId/edit",
      name: "availability-edit",
      component: Availabilities,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/user/list",
      name: "user-list",
      component: Users,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/user/:userId/assign",
      name: "assign-user",
      component: Users,
      meta: {
        requiresAuth: true
      }
    },
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  const user = auth.getUser();

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && !user) {
    next("/login");
  } else {

    // if (to.name == 'adminroute') {
    //   if (user.rol != 'admin') {
    //     next({name: 'unauthorized'})
    //   }
    // }

    // Else let them go to their next destination
    next();
  }
});

export default router;
