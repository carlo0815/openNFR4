SUMMARY = "Base packages require for image."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

ALLOW_EMPTY_${PN} = "1"

PV = "1.0"
PR = "r16"

inherit packagegroup
addtask modhbb

RDEPENDS_${PN} = "\
    oe-alliance-base \
    opennfr-enigma2 \
    opennfr-bootlogo \
    opennfr-version-info \
    opennfr-base-files \
    opennfr-settings \   
    opennfr-missing \ 
    openssh-sftp-server \
    ntfs-3g \
    hddtemp \
    busybox-cron \
    python-imaging \
    ofgwrite \
    python-gdata \
    libshowiframe \
    dvbsnoop \
    bash \
    enigma2-plugin-drivers-usbserial \ 
    "
do_modhbb() {
	  if [ "${BRAND_OEM}" = "vuplus" ] || [ "${BRAND_OEM}" = "skylake" ] || [ "${BRAND_OEM}" = "ax" ] || [ "${BRAND_OEM}" = "formuler" ]; then
		  echo "no bcm need"
	  else
	    RDEPENDS_${PN}+="\
	    bcm-au \ 
      " 
    fi	
    }  
 