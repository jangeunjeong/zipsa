<template>
  <v-container fluid grid-list-xl>
    <v-layout row wrap fill-height>
      <v-flex>
        <v-stepper v-model="e1">
          <v-stepper-header>
            <v-stepper-step :complete="e1 > 1" step="1">Name of step 1</v-stepper-step>
            <v-divider></v-divider>
            <v-stepper-step :complete="e1 > 2" step="2">Name of step 2</v-stepper-step>
            <v-divider></v-divider>
            <v-stepper-step :complete="e1 > 3" step="3">Name of step 3</v-stepper-step>
            <v-divider></v-divider>
            <v-stepper-step :complete="e1 > 4" step="4">Name of step 4</v-stepper-step>
            <v-divider></v-divider>
            <v-stepper-step :complete="e1 > 5" step="5">Name of step 5</v-stepper-step>
            <v-divider></v-divider>
            <v-stepper-step :complete="e1 > 6" step="6">Name of step 6</v-stepper-step>
            <v-divider></v-divider>
            <v-stepper-step :complete="e1 > 7" step="7">Name of step 7</v-stepper-step>
          </v-stepper-header>

          <v-stepper-items>
            <v-stepper-content step="1">
              <v-card class="mb-12" color="grey lighten-4" height="400px">
                <!-- -->
                <v-flex>
                  <h1>
                    집사가 될
                    <br />준비됐나요?
                  </h1>
                  <br />
                  <v-container fluid>
                    <v-row>
                      <v-col class="d-flex">
                        <v-flex style="width: 20vw">
                          <v-img
                            src="../../assets/ready/dog_choice.png"
                            @click="fk_animal_id = '1'"
                          />
                        </v-flex>
                        <v-flex style="width: 20vw">
                          <v-img
                            src="../../assets/ready/cat_choice.png"
                            @click="fk_animal_id = '2'"
                          />
                        </v-flex>
                      </v-col>
                      <div v-if="fk_animal_id=='1'">
                        <v-content class="mt-10">
                          네!
                          <strong class="success--text">반려견</strong>과 함께 할래요!
                        </v-content>
                      </div>
                      <div v-if="fk_animal_id=='2'">
                        <v-content class="mt-10">
                          네!
                          <strong class="success--text">반려묘</strong>와 함께 할래요!
                        </v-content>
                      </div>
                    </v-row>
                  </v-container>
                </v-flex>
                <!--  -->
              </v-card>
              <v-btn color="primary" @click="e1 = 2" :disabled="fk_animal_id == ''">다음</v-btn>
            </v-stepper-content>

            <v-stepper-content step="2">
              <v-card class="mb-12" color="grey lighten-4" height="400px">
                <!-- -->
                <v-flex align-self-center class="mb-12">
                  <v-flex class="mb-3">
                    <h2>
                      저를 위해
                      <br />한 달에 얼마를
                      <br />쓸 수 있나요?
                    </h2>
                    <v-flex xs12>
                      <!-- <v-combobox v-model="finance" :items="finance_items"></v-combobox> -->
                      <v-combobox
                        v-model="finance"
                        :items="finance_items"
                      />
                         <!-- item-text="label" -->
                        <!-- item-value="value" -->
                    </v-flex>
                    <div v-if="fk_animal_id=='1'">
                      <v-img src="../../assets/ready/dog_choice.png" width="20vh" class="mx-auto"></v-img>
                    </div>
                    <div v-if="fk_animal_id=='2'">
                      <v-img src="../../assets/ready/cat_choice.png" width="20vh" class="mx-auto"></v-img>
                    </div>
                  </v-flex>
                </v-flex>
                <!-- -->
              </v-card>
              <v-btn color="primary" @click="e1 = 3">다음</v-btn>
            </v-stepper-content>

            <v-stepper-content step="3">
              <v-card class="mb-12" color="grey lighten-4" height="400px">
                <!-- -->
                <v-flex align-self-center class="mb-12">
                  <v-flex class="mb-3">
                    <h2>
                      저를 안아줄
                      <br />수 있나요?
                    </h2>
                    <h3>(알레르기 여부)</h3>
                    <v-flex xs12>
                      <v-radio-group v-model="allergy">
                        <v-radio label="예" value="true"></v-radio>
                        <v-radio label="아니오" value="false"></v-radio>
                      </v-radio-group>
                    </v-flex>
                    <div v-if="fk_animal_id=='1'">
                      <v-img src="../../assets/ready/dog_choice.png" width="20vh" class="mx-auto"></v-img>
                    </div>
                    <div v-if="fk_animal_id=='2'">
                      <v-img src="../../assets/ready/cat_choice.png" width="20vh" class="mx-auto"></v-img>
                    </div>
                  </v-flex>
                </v-flex>
                <!-- -->
              </v-card>
              <v-btn color="primary" @click="e1 = 4" :disabled="allergy == ''">다음</v-btn>
            </v-stepper-content>

            <v-stepper-content step="4">
              <v-card class="mb-12" color="grey lighten-4" height="400px">
                <!-- -->
                <v-flex align-self-center class="mb-12">
                  <v-flex class="mb-3">
                    <h2>
                      제가 마음껏
                      <br />뛰어놀 수 있나요?
                    </h2>
                    <v-flex xs12>
                      <v-radio-group v-model="housing_type">
                        <v-radio label="아파트" value="아파트"></v-radio>
                        <v-radio label="주택" value="주택"></v-radio>
                        <v-radio label="빌라" value="빌라"></v-radio>
                      </v-radio-group>
                    </v-flex>
                    <div v-if="fk_animal_id=='1'">
                      <v-img src="../../assets/ready/dog_choice.png" width="20vh" class="mx-auto"></v-img>
                    </div>
                    <div v-if="fk_animal_id=='2'">
                      <v-img src="../../assets/ready/cat_choice.png" width="20vh" class="mx-auto"></v-img>
                    </div>
                  </v-flex>
                </v-flex>
                <!-- -->
              </v-card>
              <v-btn color="primary" @click="e1 = 5" :disabled="housing_type == ''">다음</v-btn>
            </v-stepper-content>

            <v-stepper-content step="5">
              <v-card class="mb-12 overflow-y-auto" color="grey lighten-4" style="height: 400px">
                <!-- -->
                <v-flex align-self-center class="mb-12">
                  <v-flex class="mb-3">
                    <h2>
                      저는 얼마나
                      <br />혼자 있어야 할까요?
                    </h2>
                    <h3>(일하는 시간)</h3>
                    <v-flex xs12>
                      <v-row>
                        <v-col cols="11" sm="5">
                          <v-dialog
                            ref="dialog1"
                            v-model="modal1"
                            :return-value.sync="time1"
                            persistent
                            width="290px"
                          >
                            <template v-slot:activator="{ on }">
                              <v-text-field
                                v-model="time1"
                                label="시작시간"
                                prepend-icon="access_time"
                                readonly
                                v-on="on"
                              ></v-text-field>
                            </template>
                            <v-time-picker v-if="modal1" v-model="time1" full-width>
                              <v-spacer></v-spacer>
                              <v-btn text color="primary" @click="modal1 = false">Cancel</v-btn>
                              <v-btn text color="primary" @click="$refs.dialog1.save(time1)">OK</v-btn>
                            </v-time-picker>
                          </v-dialog>
                        </v-col>

                        <v-col cols="11" sm="5">
                          <v-dialog
                            ref="dialog2"
                            v-model="modal2"
                            :return-value.sync="time2"
                            persistent
                            width="290px"
                          >
                            <template v-slot:activator="{ on }">
                              <v-text-field
                                v-model="time2"
                                label="끝나는 시간"
                                prepend-icon="access_time"
                                readonly
                                v-on="on"
                              ></v-text-field>
                            </template>
                            <v-time-picker v-if="modal2" v-model="time2" full-width>
                              <v-spacer></v-spacer>
                              <v-btn text color="primary" @click="modal2 = false">Cancel</v-btn>
                              <v-btn text color="primary" @click="$refs.dialog2.save(time2)">OK</v-btn>
                            </v-time-picker>
                          </v-dialog>
                        </v-col>
                      </v-row>
                    </v-flex>
                    <div v-if="fk_animal_id=='1'">
                      <v-img src="../../assets/ready/dog_choice.png" width="20vh" class="mx-auto"></v-img>
                    </div>
                    <div v-if="fk_animal_id=='2'">
                      <v-img src="../../assets/ready/cat_choice.png" width="20vh" class="mx-auto"></v-img>
                    </div>
                  </v-flex>
                </v-flex>
                <!-- -->
              </v-card>
              <v-btn color="primary" @click="e1 = 6">다음</v-btn>
              <!--disabled 안 한 이유 : 일을 안 할 수도 있어서 -->
            </v-stepper-content>

            <v-stepper-content step="6">
              <v-card class="mb-12" color="grey lighten-4" height="400px">
                <!-- -->
                <v-flex align-self-center class="mb-12">
                  <v-flex class="mb-3">
                    <h2>
                      가족들이
                      <br />저를 좋아할까요?
                    </h2>
                    <!-- 이쁜말..찾고싶다.-->
                    <h3>(동거인 동의여부)</h3>
                    <v-flex xs12>
                      <v-radio-group v-model="agreement">
                        <v-radio label="예" value="true"></v-radio>
                        <v-radio label="아니오" value="false"></v-radio>
                      </v-radio-group>
                    </v-flex>
                    <div v-if="fk_animal_id=='1'">
                      <v-img src="../../assets/ready/dog_choice.png" width="20vh" class="mx-auto"></v-img>
                    </div>
                    <div v-if="fk_animal_id=='2'">
                      <v-img src="../../assets/ready/cat_choice.png" width="20vh" class="mx-auto"></v-img>
                    </div>
                  </v-flex>
                </v-flex>
                <!-- -->
              </v-card>
              <v-btn color="primary" @click="e1 = 7" :disabled="agreement == ''">다음</v-btn>
            </v-stepper-content>

            <v-stepper-content step="7">
              <v-card class="mb-12" color="grey lighten-4" height="400px">
                <!-- -->
                <v-flex align-self-center class="mb-12">
                  <v-flex class="mb-3">
                    <h2>
                      저를 계속
                      <br />사랑해줄 수 있나요?
                    </h2>
                    <v-flex xs12>
                      <v-checkbox v-model="step7_checkbox">
                        <template v-slot:label>저는 소중한 생명을 가족으로 받아들이고 끝까지 책임질 준비가 되어있어요.</template>
                      </v-checkbox>
                    </v-flex>
                    <div v-if="fk_animal_id=='1'">
                      <v-img src="../../assets/ready/dog_choice.png" width="20vh" class="mx-auto"></v-img>
                    </div>
                    <div v-if="fk_animal_id=='2'">
                      <v-img src="../../assets/ready/cat_choice.png" width="20vh" class="mx-auto"></v-img>
                    </div>
                  </v-flex>
                </v-flex>
                <!-- -->
              </v-card>
              <v-btn
                color="primary"
                :disabled="step7_checkbox == false"
                @click="goSignup"
              >집사의 자격 시작하기</v-btn>
            </v-stepper-content>
          </v-stepper-items>
        </v-stepper>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "ReadySteppersPage",
  computed: {
    //연산이 필요한 변수 설정
    working_time() {
      return this.time1 + " " + this.time2;
    },
    finance_split(){
      var financeArray = this.finance.split(" ");
      return financeArray[0];
    },
    ...mapGetters(["requestHeader", "user", "getAxios"])
  },
  components: {},
  data() {
    return {
      e1: 1,
      email: this.$route.params.email, // 이전 링크에서 넘겨받은 값 (부모자식 관계아님)
      nickname: this.$route.params.nickname,
      fk_animal_id: "", 
      finance: "100 만원", 
      finance_items: ["50 만원","100 만원","200 만원","300 만원 이상"],
      allergy: false,
      housing_type: "",
      time1: null,
      time2: null,
      modal1: false,
      modal2: false,
      // working_time: "",
      agreement: false,
      step7_checkbox: ""
    };
  },
  methods: {
    goSignup() {
      var user = {
        email: this.email,
        nickname: this.nickname,
        fk_animal_id: this.fk_animal_id,
        finance: this.finance_split,
        allergy: this.allergy,
        housing_type: this.housing_type,
        working_time: this.working_time,
        agreement: this.agreement
      };
      console.log(user);
      this.getAxios
        .post("/ready/set/submit", user)
        .then(res => {
           if (res.data.result == "TRUE") {
            alert("가입이 완료되었습니다. 로그인 페이지로 이동합니다.");
            this.$router.push("/");
          } else{
            alert("문제가 생겼습니다. 다시 한 번 시도해 주세요~");
            this.$router.push("/");
          }
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  mounted() {
    // console.log(this.working_time);
  }
};
</script>

<style scoped>
</style>