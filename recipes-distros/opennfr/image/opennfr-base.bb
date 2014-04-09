SUMMARY = "Base packages require for image."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

ALLOW_EMPTY_${PN} = "1"

PV = "1.0"
PR = "r15"

inherit packagegroup

RDEPENDS_${PN} = "\
    oe-alliance-base \
    opennfr-enigma2 \
    opennfr-bootlogo \
    opennfr-version-info \
    opennfr-base-files \
    opennfr-settings \    
    openssh-sftp-server \
    ntfs-3g \
    hddtemp \
    packagegroup-base-smbfs-client \
    packagegroup-base-smbfs \
    busybox-cron \
    python-imaging \
    ofgwrite \
    libcrypto-compat-0.9.8 \
    python-gdata \
    minidlna djmount \
    mc \
    dvbsnoop \
    enigma2-plugin-drivers-usbserial \
    "
ENIGMA2_INI_PLUGINS = "\
enigma2-plugin-extensions-infopanel \
"

ENIGMA2_DVB_USB_DRV = "\
    enigma2-plugin-drivers-dvb-usb-dib0700 \
    enigma2-plugin-drivers-dvb-usb-af9015 \
    enigma2-plugin-drivers-dvb-usb-em28xx \
    enigma2-plugin-drivers-dvb-usb-dw2102 \
    enigma2-plugin-drivers-dvb-usb-it913x \
    enigma2-plugin-drivers-dvb-usb-pctv452e \
    enigma2-plugin-drivers-dvb-usb-dtt200u \
    enigma2-plugin-drivers-dvb-usb-af9035 \
    enigma2-plugin-drivers-dvb-usb-a867 \
"
