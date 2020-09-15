<template>
  <v-container
    fluid
    grid-list-xl
    style="max-width: 425px !important; margin-top: 28vh;"
    class="mx-auto"
  >
    <v-layout row wrap fill-height>
      <v-flex class="bg"></v-flex>
      <v-flex class="bg layer-2"></v-flex>
      <v-flex class="bg layer-3"></v-flex>
      <v-flex stlye="z-index: 50000;">
        <v-flex xs12>
          <v-flex class="speech-bubble">
            <v-flex
              text-center
              class="ma-0 pa-0"
              v-html="content"
              style="width: 100%; font-size: 13px; overflow: hidden;"
            ></v-flex>
          </v-flex>
          <v-flex class="dog text-center">
            <img
              :src="require(`@/assets/${img}`)"
              style="width: 40%; height: 20vh; cursor: pointer;"
              @click="changeContent()"
            />
          </v-flex>
        </v-flex>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "BreedPage",
  data() {
    return {
      content: "",
      contents: [],
      fk_animal_id: this.$store.getters.getUser.fk_animal_id,
      img: "",
      email: this.$store.getters.getUser.email
    };
  },
  computed: {
    ...mapGetters(["getAxios", "getUser"]),
    getAxios: function() {
      return this.$store.getters.getAxios;
    }
  },
  methods: {
    getContent() {
      var idx = Math.floor(Math.random() * this.contents.length);
      return this.contents[idx].script;
    },
    changeContent() {
      var tmp = this.content;
      this.content = this.getContent();

      if (tmp == this.content) {
        this.content = this.getContent();
      }
    },
    test() {
      this.getAxios
        .get("/home/script/" + this.email)
        .then(res => {
          this.contents = [];
          this.contents = res.data.scripts;
          // this.contents = res.data.scripts;
          this.content = this.contents[0].script;
          console.log(res);
          // tmp.forEach(function(element) {
          //   this.tests.push(element.script);
          // });
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  mounted() {
    if (this.fk_animal_id == 1) {
      this.img = "dog1.gif";
    } else if (this.fk_animal_id == 2) {
      this.img = "cat.gif";
    } else if (this.fk_animal_id == 3) {
      this.img = "maltese.gif";
    } else if (this.fk_animal_id == 4) {
      this.img = "golden.gif";
    } else if (this.fk_animal_id == 5) {
      this.img = "russianBlue.gif";
    } else if (this.fk_animal_id == 6) {
      this.img = "persian.gif";
    }

    this.test();
  }
};
</script>
<style scoped>
.mobileLoginForm {
  height: 100vh;
}

.speech-bubble {
  position: relative;
  background: #ffffff;
  border-radius: 0.8em;
}

.speech-bubble:after {
  content: "";
  position: absolute;
  bottom: 0px;
  left: 50%;
  border: 13px solid transparent;
  border-top-color: #ffffff;
  border-bottom: 0;
  margin-left: -12px;
  margin-bottom: -12px;
}

.dog {
  position: relative;
}

.bg {
  position: fixed;
  max-width: 425px;
  margin-left: auto;
  margin-right: auto;
  left: 0;
  right: 0;
  top: -50%;
  bottom: -50%;
  animation: slide 1s ease-in-out alternate;
  background: linear-gradient(175deg, #84e6ff 50%, #f56447 50%);
}

.layer-2 {
  animation-direction: alternate-reverse;
  animation-duration: 1s;
}

.layer-3 {
  animation-duration: 1s;
}

@keyframes slide {
  0% {
    transform: translateY(-50%);
  }
}
</style>