<template>
  <div class="popup">
      <div class="popup-inner">
          <slot />
          <h2>Hello world</h2>
          <button class="popup-close" @click="TogglePopup()">
              Close
          </button>
      </div>
  </div>
</template>

<script>
export default {
    name: 'Popup',
    props: ['TogglePopup']
}
</script>

<style scoped>
.popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
  height: 400px;
  background-color: rgb(241, 13, 192);
  border: 1px solid black;
  padding: 20px;
}

    .popup-inner {
        background-color: #FFF;
        padding: 32px;
    }


</style>