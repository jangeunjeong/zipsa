<template>
    <v-card class="overflow-hidden" outlined="">
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
          <v-flex>수료증</v-flex>
        </v-flex>
      </v-flex>
        </v-app-bar>
        <v-sheet id="scrolling-techniques-7" class="overflow-y-auto" max-height="600">
            <v-container fluid="fluid" grid-list-xl="grid-list-xl">
                <v-layout row="row" wrap="wrap">
                    <v-flex x12="x12" class="mt-12 pt-10">
                        <v-card v-if="certi_have" class="mx-auto" max-width="300" flat>
                            <v-img
                                class="black--text align-center"
                                height="200px"
                                src="../assets/dog_certi.png">
                                <v-card-title class="justify-center text-center">훌륭한 {{breed[animal_id-1]}} 집사임을 인증합니다.</v-card-title>
                            </v-img>
                        </v-card>
                        <v-card v-else max-width="300" class="mx-auto" flat>
                            <div v-if="(!certi_have)">
                                <v-img src="../assets/certiDefault.png" max-width="300px"/>
                            </div>
                            <div v-else>
                                <v-img :src="certi_img" max-width="300px"/>
                            </div>
                        </v-card>
                        <v-card max-width="300" class="mt-4 mx-auto">
                            <v-list v-if="animal_id==1||animal_id==3||animal_id==4">
                                <v-list-item v-for="item in dog_mission" :key="item.contents">
                                    <v-list-item-content>
                                        <v-list-item-title v-text="item.contents"></v-list-item-title>
                                    </v-list-item-content>

                                    <v-list-item-avatar>
                                        <v-list-item-subtitle>{{item.count}}
                                            /
                                            {{item.complete_count}}</v-list-item-subtitle>
                                    </v-list-item-avatar>
                                </v-list-item>
                            </v-list>
                            <v-list v-else>
                                <v-list-item v-for="item in cat_mission" :key="item.contents">
                                    <v-list-item-content>
                                        <v-list-item-title v-text="item.contents"></v-list-item-title>
                                    </v-list-item-content>

                                    <v-list-item-avatar>
                                        <v-list-item-subtitle>{{item.count}}
                                            /
                                            {{item.complete_count}}</v-list-item-subtitle>
                                    </v-list-item-avatar>
                                </v-list-item>
                            </v-list>
                        </v-card>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-sheet>
    </v-card>
</template>

<script>
import { mapGetters } from "vuex";
    export default {
        name: "CertificationPage",
        data() {
            return {
                certi_img: 'https://cdn.vuetifyjs.com/images/cards/store.jpg',
                animal_id: this.$route.params.id,
                certi_have: this.$route.params.certi_have,
                breed:[
                    "강아지", "고양이","말티즈","골든 리트리버","러시안 블루","페르시안"
                ],
                dog_mission: [
                    {
                        id: 1,
                        contents: '사료, 물주기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 2,
                        contents: '양치하기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 3,
                        contents: '산책하기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 4,
                        contents: '교감하기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 5,
                        contents: '건강 체크 하기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 6,
                        contents: '빗질하기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 7,
                        contents: '간식주기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 8,
                        contents: '목욕시키기',
                        count: 1,
                        complete_count: 1
                    }, {
                        id: 9,
                        contents: '동물병원 가기',
                        count: 1,
                        complete_count: 1
                    }
                ],
                cat_mission: [
                    {
                        id: 1,
                        contents: '사료, 물주기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 2,
                        contents: '양치하기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 3,
                        contents: '산책하기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 4,
                        contents: '교감하기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 5,
                        contents: '건강 체크 하기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 6,
                        contents: '빗질하기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 7,
                        contents: '간식주기',
                        count: 7,
                        complete_count: 7
                    }, {
                        id: 8,
                        contents: '목욕시키기',
                        count: 1,
                        complete_count: 1
                    }, {
                        id: 9,
                        contents: '동물병원 가기',
                        count: 1,
                        complete_count: 1
                    }, {
                        id: 10,
                        contents: '신선한 물주기',
                        count: 7,
                        complete_count: 7
                    }
                ],
                tmp_list: [],
            }
        },
        computed: {
            ...mapGetters(["requestHeader", "user", "getAxios"])
        },
        methods: {
            goBack() {
                this.$router.push("/petdetail")
            }
        },
        mounted(){
            this.getAxios
            .get("/myhome/certification/"+this.$store.getters.isLoggedIn.email)
            .then(res => {
                this.tmp_list=res.data.cntComplete
                if(this.tmp_list.lengthe==10){
                    for(var i=0;i<list.size;i++){
                        this.cat_mission[i].count=this.tmp_list[i]
                    }
                }else {
                    for(var i=0;i<this.tmp_list.length;i++){
                        this.dog_mission[i].count=this.tmp_list[i]
                    }
                }
            })
            .catch(err => {
                alert(err);
            });
        }
    }
</script>