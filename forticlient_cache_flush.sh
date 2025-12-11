#!/bin/bash

LOG="/var/log/forticlient_cache_flush.log"
exec > >(tee -a "$LOG") 2>&1

echo -e "\n[1] Flush DNS cache..."
if command -v systemd-resolve >/dev/null 2>&1; then
    sudo systemd-resolve --flush-caches >/dev/null && echo " OK" || echo " Fail"
else
    echo " Skipped (no systemd-resolve)"
fi

echo "[2] Restart NetworkManager..."
sudo systemctl restart NetworkManager >/dev/null 2>&1 && echo " OK" || echo " Fail"

echo "[3] Flush kernel caches..."
sudo sync
echo 3 | sudo tee /proc/sys/vm/drop_caches >/dev/null
echo " OK"

echo "[4] Restart NSCD (if installed)..."
if systemctl list-unit-files | grep -q '^nscd.service'; then
    sudo systemctl restart nscd >/dev/null 2>&1 && echo " OK" || echo " Fail"
else
    echo " Skipped (no nscd)"
fi

echo -e "\n[Done] Cache flush complete.\n"

