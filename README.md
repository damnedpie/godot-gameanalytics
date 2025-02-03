# Godot GameAnalytics 6.6.2 SDK
[![Godot](https://img.shields.io/badge/Godot%20Engine-3.6-blue?style=for-the-badge&logo=godotengine&logoSize=auto)](https://godotengine.org/)
[![GameAnalytics](https://img.shields.io/badge/GameAnalytics_6.6.2-black?style=for-the-badge&logoSize=auto)](https://gameanalytics.com/)
[![GitHub License](https://img.shields.io/github/license/damnedpie/godot-gameanalytics?style=for-the-badge)](#)
[![GitHub Repo stars](https://img.shields.io/github/stars/damnedpie/godot-gameanalytics?style=for-the-badge&logo=github&logoSize=auto&color=%23FFD700)](#)

GameAnalytics 6.6.2 SDK Android plugin for Godot. Built on Godot 3.6 AAR.

## Author's note

[Based on the official repository.](https://github.com/GameAnalytics/GA-SDK-GODOT) Main difference between my repo and the official one is that this repo uses up to date dependencies and is suitable for Godot 3.x, plus a well documented GDScript wrapper for the SDK. Please take into account that GameAnalytics is not just an MMP but basically a framework in terms of how you set your in-game events, so make sure you study the docs thoroughly before using it.

## Setup

### Project integration

Grab the``GodotGameAnalytics`` plugin binary (.aar) and config (.gdap) from the releases page and put both into ``res://android/plugins``. For easy start, you can also use ``GodotGameAnalytics.gd`` script as an autoload.

Make sure that you provide the AppKey and the SecretKey during initialization.
