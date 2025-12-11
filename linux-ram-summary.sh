watch -n 1 '
free -h | awk "
/Mem:/ {
  total=\$2;
  used=\$3;
  free=\$4;
  cache=\$6;
  available=\$7;
}
/Swap:/ {
  swtotal=\$2;
  swused=\$3;
}
END {
  print \"You have \" total \" RAM.\";
  print \"Only \" used \" is actively used.\";
  print free \" RAM is sitting idle (free).\"; 
  print \"Linux is using \" cache \" as cache to speed up things.\";
  print \"You still have \" available \" available to use.\";
  print \"Swap (\" swtotal \") is \" (swused==\"0B\" ? \"unused, which means your system is running smoothly with enough RAM.\" : \"being used.\");
}"
'


