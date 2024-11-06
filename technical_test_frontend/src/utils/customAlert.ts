import Swal from "sweetalert2";

export function alertSuccessApi(message: string) {
  Swal.fire({
      title: 'Message From Server',
      text: message,
      icon: 'success',
      confirmButtonText: 'ok'
  });

}
export function alertErrorApi(message: string) {
  Swal.fire({
      title: 'Error Message from Server',
      text: message,
      icon: 'error',
      confirmButtonText: 'ok'
  });

}
export function alertWarningApi(message: string) {
  Swal.fire({
      title: 'Message From Server',
      text: message,
      icon: 'warning',
      confirmButtonText: 'ok'
  });
}

export function alertSuccess(message: string) {
  Swal.fire({
      title: 'Success',
      text: message,
      icon: 'success',
      confirmButtonText: 'ok'
  });
}

export function alertWarning(message: string) {
  Swal.fire({
      title: 'Warning',
      text: message,
      icon: 'warning',
      confirmButtonText: 'ok'
  });
}

export function alertInfo(message: string) {
  Swal.fire({
      title: 'Info',
      text: message,
      icon: 'info',
      confirmButtonText: 'ok'
  });
}