import Vue from "vue";
import Router from "vue-router";
import LoginPage from "./views/LoginPage.vue";
import MainPage from "./views/MainPage.vue";
import BreedPage from "./views/BreedPage.vue";
import MissionPage from "./views/MissionPage.vue";
import StrayAnimalPage from "./views/StrayAnimalPage.vue";
import StrayAnimalDetailPage from "./views/StrayAnimalDetailPage.vue";
import StudyPage from "./views/StudyPage.vue";
import QuizPage from "./views/QuizPage.vue";
import ReQuizPage from "./views/ReQuizPage.vue";
import LearnPage from "./views/LearnPage.vue";
import QuestionPage from "./views/QuestionPage.vue";
import MyPage from "./views/MyPage.vue";
import MyhomePage from "./views/MyhomePage.vue";
import MyhomeSettingPage from "./views/MyhomeSettingPage.vue";
import MyhomePetRegPage from "./views/MyhomePetRegPage.vue";
import MyhomePetDetailPage from "./views/MyhomePetDetailPage.vue";
import CertificationPage from "./views/CertificationPage.vue";
import EJ_Test from "./views/EJ_Test.vue";
import HealthChatPage from "./views/HealthChatPage.vue";
import BehaviorChat from "./views/BehaviorChat.vue";
import NurtureChatPage from "./views/NurtureChatPage.vue";

import ReadySteppersPage from "./views/ready/ReadySteppersPage.vue";
import MissionListPage from "./views/mission7/MissionListPage.vue";
import MissionDetailPage from "./views/mission7/MissionDetailPage.vue";
// import MissionDetail_DogPage from "./views/mission7/MissionDetail_DogPage.vue";
// import MissionDetail_CatPage from "./views/mission7/MissionDetail_CatPage.vue";



Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "login",
      component: LoginPage,
    },
    {
      path: "/main",
      name: "main",
      component: MainPage,
    },
    {
      path: "/breed",
      name: "breed",
      component: BreedPage,
    },
    {
      path: "/mission",
      name: "mission",
      component: MissionPage,
    },
    {
      path: "/strayanimal",
      name: "strayanimal",
      component: StrayAnimalPage,
    },
    {
      path: "/strayanimaldetail",
      name: "strayanimaldetail",
      component: StrayAnimalDetailPage,
    },
    {
      path: "/study",
      name: "study",
      component: StudyPage,
    },
    {
      path: "/quiz",
      name: "quiz",
      component: QuizPage,
    },
    {
      path: "/requiz",
      name: "requiz",
      component: ReQuizPage,
    },
    {
      path: "/learn",
      name: "learn",
      component: LearnPage,
    },
    {
      path: "/question",
      name: "question",
      component: QuestionPage,
    },
    {
      path: "/mypage",
      name: "mypage",
      component: MyPage,
	},
	
	{
		path: '/myhome',
		name: 'MyhomePage',
		component: MyhomePage
	},
	{
		path: '/myhomesetting',
		name: 'MyhomeSettingPage',
		component: MyhomeSettingPage
	},
	{
		path: '/petreg',
		name: 'MyhomePetRegPage',
		component: MyhomePetRegPage
	},
	{
		path: '/petdetail',
		name: 'MyhomePetDetailPage',
		component: MyhomePetDetailPage
	},
	{
		path: '/certi',
		name: 'CertificationPage',
		component: CertificationPage
  },
  {
		path: '/health',
		name: 'HealthChatPage',
		component: HealthChatPage
  },
  {
		path: '/behavior',
		name: 'BehaviorChat',
		component: BehaviorChat
  },
  {
		path: '/nurture',
		name: 'NurtureChatPage',
		component: NurtureChatPage
	},
	{
		path: '/ej',
		name: 'EJ_Test',
		component: EJ_Test
  },
  {
		path: '/readysteppers',
		name: 'ReadySteppersPage',
		component: ReadySteppersPage
  },
  {
    path: "/missionlistpage",
    name: "missionlistpage",
    component: MissionListPage,
  },
  {
    path: "/missiondetailpage",
    name: "missiondetailpage",
    component: MissionDetailPage,
  },
  // {
  //   path: "/missiondetail_dogpage",
  //   name: "missiondetail_dogpage",
  //   component: MissionDetail_DogPage,
  // },
  // {
  //   path: "/missiondetail_catpage",
  //   name: "missiondetail_catpage",
  //   component: MissionDetail_CatPage,
  // },
  {
    path: "/*",
    name: "404error",
    redirect: { name: "main" },
  }
  ],
});
