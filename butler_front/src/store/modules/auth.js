import jwt_decode from "jwt-decode";
import axios from "axios";
import store from "..";

const axiosURL = axios.create({
  // baseURL: "http://52.79.100.152:8080/api/"
  // baseURL: "http://192.168.31.72:8080/api/"
  // baseURL: "https://i02b206.p.ssafy.io/api/"
  // baseURL: "http://172.19.66.244:8080/api/"
  // baseURL: "http://192.168.43.78:8080/api/"
  // baseURL: "https://e02lib1.p.ssafy.io/api/"
  // baseURL: "http://192.168.43.109:8888/api/"
  baseURL: "http://13.124.63.164:8888/api/"

})

axiosURL.interceptors.response.use(
  function(response) {
    // Do something with response data
    if (response.data.msg === "Token Expired") {
      store.dispatch("logout");
    }
    return response;
  },
  function(error) {
    // Do something with response error
    return Promise.reject(error);
  }
);

const state = {
  //token: null,
  //user: null,
  accessToken : null,
  email : null,
  nickname : null,
  fk_animal_id : null,
  mission_state : null,
  URL: axiosURL ? axiosURL || null : null,
};

// data(state)를 변경하지 않음
// data를 원본 그대로 혹은 가공된 데이터를 사용
const getters = {
  isLoggedIn: function(state) {
    return state.accessToken ? state : false
  },
  requestHeader: function(state) {
    return {
      headers: {
        token: state.accessToken
      }
    }
  },
  getUser: function(state) {
    return state ? state : null
  },
  geFk_animal_id: function(state) {
    return state.fk_animal_id ? state.fk_animal_id : null
  },
  getAxios: function(state) {
    return state.URL ? state.URL : null
  }
}

// 상태(토큰)을 받아와서 state를 update
const mutations = {
  // setToken: function(state, token) {
  //   state.token = token
  // },
  // setUser: function(state, token) {
  //   state.user = token ? jwt_decode(token).User : token
  // },
  // setUserBook: function(state, userBook) {
  //   state.userBook = userBook
  // },
  setAccessToken: function(state,accessToken){
    state.accessToken = accessToken
  },
  setEmail: function(state,email){
    state.email = email
  },
  setNickName: function(state,nickname){
    state.nickname = nickname
  },
  setFk_animal_id: function(state,fk_animal_id){
    state.fk_animal_id = fk_animal_id
  },
  setMission_state: function(state,mission_state){
    state.mission_state = mission_state
  }

}

// 비동기 로직(axios 로 django 서버에 로그인 / 로그아웃 요청)
// options
// action 에서 사용할 수 있도록 만든 객체 / vuex에서 제공하는 actions 함수에서 사용할 수 있는 option 들이 있는 객체
const actions = {
  // commit은 첫번째 인자로 mutations에 정의한 함수를 받는다.
  // 두번째 인자로 토큰을 받아서, mutations에 정의된 함수를 통해 state를 변경한다.
  login: function(options, accessToken) {
    options.commit('setAccessToken', accessToken)
    //options.commit('setUser', token)
  },
  // 로그아웃의 경우 추가로 받는 인자는 없고 token의 상태를 null로 변경한다.
  logout: function(options) {
    options.commit('setAccessToken')
    options.commit('setEmail')
    options.commit('setNickName')
    options.commit('setFk_animal_id')
    options.commit('setMission_state')
  },
  startLogin : function(options,loginData){
    options.commit('setAccessToken',loginData.accessToken)
    options.commit('setEmail',loginData.email)
    options.commit('setNickName',loginData.nickname)
    options.commit('setFk_animal_id',loginData.fk_animal_id)
    options.commit('setMission_state',loginData.mission_state)
  }
}

export default {
  state,
  mutations,
  actions,
  getters,
};
