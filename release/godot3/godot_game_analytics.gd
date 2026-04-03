extends Node

# Official Godot usage example: https://github.com/GameAnalytics/GA-SDK-GODOT/blob/master/example/main_scene.gd

# About custom fields in events: https://docs.gameanalytics.com/integrations/sdk/godot/event-tracking#custom-event-fields

var _gameAnalytics : JNISingleton

const GAME_KEY : String = ""
const SECRET_KEY : String = ""

func output(message) -> void:
	print("%s: %s" % [name, message])

func initialize() -> void:
	if Engine.has_singleton("GameAnalytics"):
		_gameAnalytics = Engine.get_singleton("GameAnalytics")

		setEnabledInfoLog(true) # Disable in production
		setEnabledVerboseLog(true) # Disable in production

		configureAutoDetectAppVersion(true)

		init(GAME_KEY, SECRET_KEY)
		output("Game Analytics initialized")
	else:
		output("Game Analytics singleton not found")


# Custom Dimensions: https://docs.gameanalytics.com/advanced-tracking/custom-dimensions/
# Argument arrays must be arrays of strings like ["ninja", "samurai"]

# Called BEFORE init
func configureAvailableCustomDimensions01(customDimensions:Array) -> void:
	_gameAnalytics.configureAvailableCustomDimensions01(customDimensions)

# Called BEFORE init
func configureAvailableCustomDimensions02(customDimensions:Array) -> void:
	_gameAnalytics.configureAvailableCustomDimensions02(customDimensions)

# Called BEFORE init
func configureAvailableCustomDimensions03(customDimensions:Array) -> void:
	_gameAnalytics.configureAvailableCustomDimensions03(customDimensions)

# Resource currencies
# These methods are used to specify what resource currencies and resource item types are available in the game.

# Called BEFORE init
# Input example: ["gold", "gems", "diamonds"]
func configureAvailableResourceCurrencies(resourceCurrencies:Array) -> void:
	_gameAnalytics.configureAvailableResourceCurrencies(resourceCurrencies)

# Called BEFORE init
# Input example: ["boosts", "lives"]
func configureAvailableResourceItemTypes(resourceItemTypes:Array) -> void:
	_gameAnalytics.configureAvailableResourceItemTypes(resourceItemTypes)

# Called BEFORE init
# Can be used to manually set the current game build name like "0.7.4"
func configureBuild(build:String) -> void:
	_gameAnalytics.configureBuild(build)

# Called BEFORE init
# Can be used to automatically detect the current game build instead of setting it manually
func configureAutoDetectAppVersion(enabled:bool) -> void:
	_gameAnalytics.configureAutoDetectAppVersion(enabled)

# Called BEFORE init
# Not preferrable to use because SDK generates those automatically
# https://docs.gameanalytics.com/integrations/sdk/godot#custom-userid
func configureUserId(userId:String) -> void:
	_gameAnalytics.configureUserId(userId)

# Called BEFORE init
# Short messages indicating SDK actions
# Disable it for release builds
func setEnabledInfoLog(enabled:bool) -> void:
	_gameAnalytics.setEnabledInfoLog(enabled)

# Called BEFORE init
# Verbose messages for debugging and troubleshooting
# Disable it for release builds
func setEnabledVerboseLog(enabled:bool) -> void:
	_gameAnalytics.setEnabledVerboseLog(enabled)

# Called BEFORE init
# If enabled, sends error events for uncaught exceptions. Enabled by default
func setEnabledErrorReporting(enabled:bool) -> void:
	_gameAnalytics.setEnabledErrorReporting(enabled)

# Called BEFORE init
# Enabled by default. If disabled, events are not submitted
func setEnabledEventSubmission(enabled:bool) -> void:
	_gameAnalytics.setEnabledEventSubmission(enabled)

# Probably called before init?
# https://docs.gameanalytics.com/integrations/sdk/ios/game-ops#custom-event-fields
func setGlobalCustomEventFields(customFields:String) -> void:
	_gameAnalytics.setGlobalCustomEventFields(customFields)

# Initializes the SDK
func init(gameKey:String, secretKey:String) -> void:
	var versionInfo : Dictionary = Engine.get_version_info()
	var versionString = "%s.%s.%s" % [versionInfo["major"], versionInfo["minor"], versionInfo["patch"]]
	_gameAnalytics.init(gameKey, secretKey, versionString)

# Event logging: https://docs.gameanalytics.com/event-types/
# Godot examples: https://github.com/GameAnalytics/GA-SDK-GODOT/blob/master/example/main_scene.gd#L49

# Logs a business event (IAP): https://docs.gameanalytics.com/event-types/business-events
# Expected dictionary structure:
#	"currency": String,
#	"amount": int or float,
#	"itemType": String,
#	"itemId": String,
#	"cartType": String,
#	"customFields" : String, (optional) (JSON string?)
#	"mergeFields" : bool (optional, false by default)
#	"receipt": String,
#	"signature": String
func addBusinessEvent(options:Dictionary) -> void:
	_gameAnalytics.addBusinessEvent(options)

# Logs a resource event: https://docs.gameanalytics.com/event-types/resource-events
# Expected dictionary structure:
#	"flowType": "Source" OR "Sink", "Source" means player gaining resource and "Sink" meaning they lose resource
#	"currency": String,
#	"amount": int or float,
#	"itemType": String,
#	"itemId": String
#	"customFields" : String, (optional) (JSON string?)
#	"mergeFields" : bool (optional, false by default)
func addResourceEvent(options:Dictionary) -> void:
	_gameAnalytics.addResourceEvent(options)

# Logs a progression event: https://docs.gameanalytics.com/event-types/progression-events
# Expected dictionary structure
#	"progressionStatus": "Start" OR "Complete" OR "Fail",
#	"progression01": String,
#	"progression02": String, (optional)
#	"progression03": String, (optional)
#	"score" : int or float, (optional)
#	"customFields" : String, (optional) (JSON string?)
#	"mergeFields" : bool (optional, false by default)
func addProgressionEvent(options:Dictionary) -> void:
	_gameAnalytics.addProgressionEvent(options)

# Logs a design event: https://docs.gameanalytics.com/event-types/design-events
# Expected dictionary structure
#	"eventId" : String, can be hierarchical separated by colons e.g. "Achievement:Killing:Neutral:100_Kills"
#	"value" : Number, some meaningful value for the event (optional)
#	"customFields": String (optional) (JSON string?)
#	"mergeFields" : bool (optional, false by default)
func addDesignEvent(options:Dictionary) -> void:
	_gameAnalytics.addDesignEvent(options)

# Logs an error event: https://docs.gameanalytics.com/event-types/error-events
# Expected dictonary structure
#	"severity" : "Debug" OR "Info" OR "Warning" OR "Error" OR "Critical"
#	"message" : String
#	"customFields": String (optional) (JSON string?)
#	"mergeFields" : bool (optional, false by default)
func addErrorEvent(options:Dictionary) -> void:
	_gameAnalytics.addErrorEvent(options)

# Logs an ad event: https://docs.gameanalytics.com/event-types/ad-events
# Expected dictionary structure
#	"adAction" : "Clicked" OR "Show" OR "FailedShow" OR "RewardReceived"
#	"adType" : "Video" OR "RewardedVideo" OR "Playable" OR "Interstitial" OR "OfferWall" OR "Banner"
#	"adSdkName" : String (no spaces or underscores)
#	"adPlacement" : String
#	"duration" : int or float
#	"noAdReason" : "Unknown" OR "Offline" OR "NoFill" OR "InternalError" OR "InvalidRequest" OR "UnableToPrecache"
#	"customFields": String (optional) (JSON string?)
#	"mergeFields" : bool (optional, false by default)
func addAdEvent(options:Dictionary) -> void:
	_gameAnalytics.addAdEvent(options)

# Adds an ILRD event: https://docs.gameanalytics.com/integrations/advertising/admob
# Currently testing it; custom fields are not yet supported by the plugin.
func addImpressionEvent(networkName:String, networkVersion:String, options:Dictionary) -> void:
	_gameAnalytics.addImpressionEvent(networkName, networkVersion, options)

# Custom Dimensions: https://docs.gameanalytics.com/advanced-tracking/custom-dimensions/
func setCustomDimension01(dimension:String) -> void:
	_gameAnalytics.setCustomDimension01(dimension)

func setCustomDimension02(dimension:String) -> void:
	_gameAnalytics.setCustomDimension02(dimension)

func setCustomDimension03(dimension:String) -> void:
	_gameAnalytics.setCustomDimension03(dimension)

# Can be called at any given time
func setEnabledManualSessionHandling(enabled:bool) -> void:
	_gameAnalytics.setEnabledManualSessionHandling(enabled)

# Can be called at any given time if manual session handling is enabled
func startSession() -> void:
	_gameAnalytics.startSession()

# Can be called at any given time if manual session handling is enabled
func endSession() -> void:
	_gameAnalytics.endSession()

# Remote configs https://docs.gameanalytics.com/features/ab-testing/integration#register-for-events
func getRemoteConfigsValueAsString(options:Dictionary) -> String:
	return _gameAnalytics.getRemoteConfigsValueAsString(options)

func isRemoteConfigsReady() -> bool:
	return _gameAnalytics.isRemoteConfigsReady()

func getRemoteConfigsContentAsString() -> String:
	return _gameAnalytics.getRemoteConfigsContentAsString()
