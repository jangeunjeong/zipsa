<template>
    <v-card class="overflow-hidden" flat>
        <v-app-bar
            absolute="absolute"
            color="#b5c7d3"
            height= "78"
            elevate-on-scroll="elevate-on-scroll"
            scroll-target="#scrolling-techniques-7"
            centerTitle:="centerTitle:"
            true="true">
            <v-spacer></v-spacer>
            <v-flex xs12>
        <v-flex d-flex class="text-center py-2">
          <v-flex style="max-width: 15px !important; z-index: 80000;">
            <v-icon @click="goBack()">mdi-chevron-left</v-icon>
          </v-flex>
          <v-flex>내 정보</v-flex>
        </v-flex>
      </v-flex>
            <v-spacer></v-spacer>
            <!-- <v-btn to="/myhome" icon class="pr-4">
                <v-icon>mdi-account-circle</v-icon>
            </v-btn> -->
        </v-app-bar>
        <v-sheet id="scrolling-techniques-7" class="overflow-y-auto" max-height="600">
          <v-container> 
              <v-simple-table class="mt-8 pt-8">
              <template v-slot:default>
                <tbody>
                  <tr>
                    <td class="forTd">이름</td>
                      <td class="forTd2">
                          <v-text-field
                          v-model="nickname"
                          readonly
                          ></v-text-field>
                      </td>
                  </tr>  
                  <tr>
                    <td class="forTd">이메일</td>
                      <td class="forTd2">
                          <v-text-field
                          v-model="email"
                          readonly
                          ></v-text-field>
                      </td>
                  </tr>  
                  <tr>
                    <td class="forTd">재정상태</td>
                    <td class="forTd2">
                        <v-text-field
                          v-model="finance"
                          readonly
                          suffix="만원"
                          ></v-text-field>
                      </td>
                  </tr>
                  <tr>
                    <td class="forTd">남은코인</td>
                    <td class="forTd2">
                        <v-text-field
                          v-model="change"
                          readonly
                          suffix="만원"
                          ></v-text-field>
                      </td>
                  </tr>
                  <tr>
                    <td class="forTd">알레르기</td>
                    <td class="forTd2">
                      <v-radio-group v-model="allergy" row>
                          <v-radio color="red" label="있어요" value="yes"></v-radio>
                          <v-radio color="primary" label="없어요" value="no"></v-radio>
                      </v-radio-group>
                      </td>
                  </tr>
                  <tr>
                    <td class="forTd">주거형태</td>
                    <td class="forTd2">
                      <v-radio-group v-model="housing_type" column>
                        <v-radio
                          label="아파트"
                          color="success"
                          value="아파트"
                        ></v-radio>
                        <v-radio
                          label="주택"
                          color="info"
                          value="주택"
                        ></v-radio>
                        <v-radio
                          label="빌라"
                          color="warning"
                          value="빌라"
                        ></v-radio>
                      </v-radio-group>
                      </td>
                  </tr>
                  <tr>
                    <td class="forTd">일하는시간</td>
                    <td class="forTd2">
                      <v-dialog
                          ref="dialog1"
                          v-model="modal1"
                          :return-value.sync="time1"
                          persistent
                          width="250px"
                      >
                          <template v-slot:activator="{ on }">
                          <v-text-field
                              v-model="time1"
                              label="출근 시간"
                              prepend-icon="access_time"
                              readonly
                              v-on="on"
                          ></v-text-field>
                          </template>
                          <v-time-picker
                          v-if="modal1"
                          v-model="time1"
                          full-width
                          >
                          <v-spacer></v-spacer>
                          <v-btn text color="primary" @click="modal1 = false">Cancel</v-btn>
                          <v-btn text color="primary" @click="$refs.dialog1.save(time1)">OK</v-btn>
                          </v-time-picker>
                      </v-dialog>
                      <v-dialog
                          ref="dialog2"
                          v-model="modal2"
                          :return-value.sync="time2"
                          persistent
                          width="250px"
                      >
                          <template v-slot:activator="{ on }">
                          <v-text-field
                              v-model="time2"
                              label="퇴근 시간"
                              prepend-icon="access_time"
                              readonly
                              v-on="on"
                          ></v-text-field>
                          </template>
                          <v-time-picker
                          v-if="modal2"
                          v-model="time2"
                          full-width
                          >
                          <v-spacer></v-spacer>
                          <v-btn text color="primary" @click="modal2 = false">Cancel</v-btn>
                          <v-btn text color="primary" @click="$refs.dialog2.save(time2)">OK</v-btn>
                          </v-time-picker>
                      </v-dialog>
                    </td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
            <v-btn
              block="block"
              class="mt-4"
              color="#b5c7d3"
              dark="dark"
              @click="updateUser">수정하기
            </v-btn>
                  <br><br>

          </v-container>
        </v-sheet>
    </v-card>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "MyhomeSettingPage",
  data () {
    return {
      show: false,
      nickname: '',
      email: this.$store.getters.isLoggedIn.email,
      finance: '',
      change:3,
      ballergy:'',
      allergy: '',
      housing_type: '',
      working_time: null,
      time1: null,
      time2: null,
      modal1: false,
      modal2: false,
    }
  },
  computed: {
    check_working_time() {
      return this.time1 + " " + this.time2;
    },
    check_allergy(){
      if(this.allergy=='yes'){
        return true;
      }else{
        return false;
      }
    },
    ...mapGetters(["requestHeader", "user", "getAxios"])
  },
  mounted(){
    this.getAxios
            .get("/myhome/profile/detail/"+this.$store.getters.isLoggedIn.email)
            .then(res => {
                this.nickname=res.data.user.nickname,
                this.finance=res.data.user.finance,
                this.ballergy=res.data.user.allergy,
                this.housing_type=res.data.user.housing_type,
                this.working_time=res.data.user.working_time,
                this.time1=this.working_time.split(" ")[0],
                this.time2=this.working_time.split(" ")[1]
                if (this.ballergy) this.allergy = 'yes'
                else this.allergy = 'no'
            })
            .catch(err => {
                alert(err);
            });
  },
  methods: {
    goBack() {
      this.$router.push("/myhome")
    },
    updateUser(){
      var user = {
        email: this.email,
        nickname: this.nickname,
        fk_animal_id: this.fk_animal_id,
        finance: this.finance_split,
        allergy: this.check_allergy,
        housing_type: this.housing_type,
        working_time: this.check_working_time
      };
      console.log(user)
      this.getAxios
        .post("/myhome/profile/update", user)
        .then(res => {
          alert("수정되었습니다.")
          this.$router.push('/main')
        })
        .catch(err => {
          console.log(err);
        });
    },
    updateProfile() {
      for (var i=0; i < this.langSelect.length; i++) {
        if (this.langSelect[i] == "C++") {
          this.langSelect[i] = 'Cpp'
        } else if (this.langSelect[i] == 'C#') {
          this.langSelect[i] = 'Csharp'
        }
      }
      let data = {
        'techStack': this.langSelect.join(),
        'wishHope': this.firm,
        'wishJob': this.positionSelect.join()
      }
      baseURL.put('user/'+cookie.cookieUser()+'/updateprofile?techStack='+data.techStack+'&wishHope='+data.wishHope+'&wishJob='+data.wishJob)
        .then(()=>{
          alert("프로필 수정이 완료되었습니다")
        })
    },
    secessionUser() {
      if(confirm("정말 탈퇴하시겠습니까?")) {
        baseURL.put('user/' + cookie.cookieUser() + '/secession?tmp='+cookie.accessToken())
          .then(()=> {
            var date1 = new Date();
            this.$store.commit('isLogout')
            this.$store.commit('clearInfo')
            document.cookie = 'login_user' + "= " + "; expires=" + date1.toUTCString() + "; path=/";
            document.cookie = 'login_id' + "= " + "; expires=" + date1.toUTCString() + "; path=/";
            document.cookie = 'jwt-auth-token' + "= " + "; expires=" + date1.toUTCString() + "; path=/";
            document.cookie = 'access-token' + "= " + "; expires=" + date1.toUTCString() + "; path=/";
            document.cookie = 'login_name' + "= " + "; expires=" + date1.toUTCString() + "; path=/";
            alert("탈퇴되셨습니다.")
            this.$router.push('/')
          })
      }
    }
  },
}
</script>

<style lang="scss">
.forTd {
    width: 110px;
  text-align: center;
  color: rgb(165, 141, 127);
  font-size: 0.9rem !important;
  font-weight: bold;
  text-shadow: 0px 0px 0px rgb(15, 76, 129);
}
.forTd2 > section {
  font-family: 'Cafe24Dangdanghae';
  font-size: 1.1rem;
  padding-top: 20px;
}
</style>