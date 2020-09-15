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
          <v-flex>반려동물 관리</v-flex>
        </v-flex>
      </v-flex>
        </v-app-bar>
        <v-sheet id="scrolling-techniques-7" class="overflow-y-auto" max-height="600">
            <div>
                <v-container fluid="fluid" grid-list-xl="grid-list-xl">
                    <v-layout row="row" wrap="wrap">
                        <v-flex sm8="sm8" offset-sm-2="offset-sm-2">
                            <v-card class="mt-12 pt-8" exact="exact" flat="flat">
                                <v-card class="pt-2 pb-4 justify-center text-center" color="#b5c7d3">
                                    <v-btn
                                        text="text"
                                        fab="fab"
                                        :disabled="dialog"
                                        :loading="dialog">
                                        <v-avatar size="64" v-on="on">
                                            <v-img max-width="70px" :src="animal.img || userDefaultImg" alt="userImg"></v-img>
                                        </v-avatar>
                                    </v-btn>
                                </v-card>
                                <v-card
                                    tile="tile"
                                    outlined="outlined"
                                    tag="div"
                                    width="100%"
                                    height="300px"
                                    class="pt-3 pl-7 pr-7"
                                    flat="flat"
                                    :elevation="hover ? 13 : 0">
                                    <v-content class="font-weight-bold pt-2">이름은 무엇인가요?</v-content>
                                    <v-text-field v-model="animal.name" required="required"></v-text-field>

                                    <v-content class="font-weight-bold pt-2">품종은 무엇인가요?</v-content>
                                    <v-text-field v-model="animal.breed" readonly="readonly" required="required"></v-text-field>
                                    <div class="justify-center text-center" max-width="200px" @click="testtest">
                                        <v-content v-if="(!certi_have)" class="teal lighten-1">아직 인증서가 없어요</v-content>
                                        <v-content v-else class="lime lighten-1">인증서가 있어요</v-content>
                                    </div>
                                    <!-- <v-card max-width="300" class="mx-auto" outlined="" @click="testtest">
                                        <div v-if="(!certi_have)">
                                            <v-img src="../assets/certiDefault.png" max-width="300px"/>
                                        </div>
                                        <div v-else>
                                            <v-img :src="certi_img" max-width="300px"/>
                                        </div>
                                    </v-card> -->
                                </v-card>
                            </v-card>
                            <!-- <br> -->
                            <!-- <v-btn
                                block="block"
                                class="mt-4"
                                color="#b5c7d3"
                                dark="dark"
                                @click="updatePet">수정하기</v-btn> -->
                        </v-flex>
                    </v-layout>
                </v-container>
            </div>
        </v-sheet>
    </v-card>
</template>

<script>
    export default {
        name: "MyhomePetDetailPage",
        data() {
            return {
                animal: {
                    id: this.$route.params.id,
                    name: this.$route.params.name, // 이전 링크에서 넘겨받은 값 (부모자식 관계아님)
                    breed: this.$route.params.breed,
                    img: this.$route.params.img,
                },
                certi_have: this.$route.params.certi_have,
            }
        },
        watch: {
            dialog(val) {
                if (!val) 
                    return

                setTimeout(() => (this.dialog = false), 4000)
            }
        },
        methods: {
            goBack() {
                this.$router.push("/myhome")
            },
            // 이미지 바꿔서 보여주는 부분
            createImage(file) {
                var image = new Image();
                var reader = new FileReader();
                var vm = this;

                reader.onload = e => {
                    vm.userImg = e.target.result;
                };
                reader.readAsDataURL(file);
            },
            updatePet() {
                if (this.name == '') {
                    alert("반려친구의 이름을 지어주세요.")
                } else {
                    alert("수정이 완료되었습니다.")
                    this.$router.push('/myhome')
                }
            },
            testtest(){
                   this.$router.push({
              name: "CertificationPage",
              params: {
                  id: this.animal.id,
                  certi_have: this.certi_have,
              }
            });
            },
        },
    }
</script>