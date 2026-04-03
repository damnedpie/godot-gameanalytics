package com.onecat.godotgameanalytics;

import android.util.Log;

import com.gameanalytics.sdk.GAErrorSeverity;
import com.gameanalytics.sdk.GAProgressionStatus;
import com.gameanalytics.sdk.GAResourceFlowType;
import com.gameanalytics.sdk.GAAdAction;
import com.gameanalytics.sdk.GAAdType;
import com.gameanalytics.sdk.GAAdError;
import com.gameanalytics.sdk.GameAnalytics;

import org.godotengine.godot.Dictionary;
import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.UsedByGodot;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

@SuppressWarnings("unused")
public class GodotGameAnalytics extends GodotPlugin {

    public GodotGameAnalytics(Godot godot)
    {
        super(godot);
    }

    @Override
    public String getPluginName()
    {
        return "GameAnalytics";
    }

    @UsedByGodot
    public void configureAvailableCustomDimensions01(String[] customDimensions)
    {
        GameAnalytics.configureAvailableCustomDimensions01(customDimensions);
    }

    @UsedByGodot
    public void configureAvailableCustomDimensions02(String[] customDimensions)
    {
        GameAnalytics.configureAvailableCustomDimensions02(customDimensions);
    }

    @UsedByGodot
    public void configureAvailableCustomDimensions03(String[] customDimensions)
    {
        GameAnalytics.configureAvailableCustomDimensions03(customDimensions);
    }

    @UsedByGodot
    public void configureAvailableResourceCurrencies(String[] resourceCurrencies)
    {
        GameAnalytics.configureAvailableResourceCurrencies(resourceCurrencies);
    }

    @UsedByGodot
    public void configureAvailableResourceItemTypes(String[] resourceItemTypes)
    {
        GameAnalytics.configureAvailableResourceItemTypes(resourceItemTypes);
    }

    @UsedByGodot
    public void configureBuild(String build)
    {
        GameAnalytics.configureBuild(build);
    }

    @UsedByGodot
    public void configureAutoDetectAppVersion(boolean flag)
    {
        GameAnalytics.configureAutoDetectAppVersion(flag);
    }

    @UsedByGodot
    public void configureUserId(String uId)
    {
        GameAnalytics.configureUserId(uId);
    }

    @UsedByGodot
    public void init(String gameKey, String secretKey, String godotVersion)
    {
        GameAnalytics.configureSdkGameEngineVersion("godot "+godotVersion);
        GameAnalytics.initialize(getActivity(), gameKey, secretKey);
    }

    @UsedByGodot
    public void addBusinessEvent(Dictionary options)
    {
        String currency = "";
        int amount = 0;
        String itemType = "";
        String itemId = "";
        String cartType = "";
        String receipt = "";
        String store = "";
        String signature = "";
        String fields = "";
        boolean mergeFields = false;

        if(options != null)
        {
            Set<String> keys = options.keySet();
            for(String key : keys)
            {
                if(key.equals("currency"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        currency = value + "";
                    }
                }
                else if(key.equals("amount"))
                {
                    Object value = options.get(key);
                    if(value instanceof Number)
                    {
                        Number n = (Number)value;
                        amount = n.intValue();
                    }
                    else
                    {
                        Log.d("GameAnalytics", "'" + key + "' value has the wrong type: " + value.getClass());
                    }
                }
                else if(key.equals("itemType"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        itemType = value + "";
                    }
                }
                else if(key.equals("itemId"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        itemId = value + "";
                    }
                }
                else if(key.equals("cartType"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        cartType = value + "";
                    }
                }
                else if(key.equals("receipt"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        receipt = value + "";
                        store = "google_play";
                    }
                }
                else if(key.equals("signature"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        signature = value + "";
                    }
                }
                else if (key.equals("customFields"))
                {
                    Object value = options.get(key);
                    if (value != null)
                    {
                        fields = value + "";
                    }
                }
                else if (key.equals("mergeFields")) {
                    Object value = options.get(key);
                    if (value instanceof Boolean) {
                        Boolean b = (Boolean) value;
                        mergeFields = b.booleanValue();
                    }
                }
            }
        }
        if (!fields.isEmpty())
        {
            GameAnalytics.addBusinessEvent(currency, amount, itemType, itemId, cartType, receipt, store, signature, fields, mergeFields);
        }
        else
        {
            GameAnalytics.addBusinessEvent(currency, amount, itemType, itemId, cartType, receipt, store, signature);
        }

    }

    @UsedByGodot
    public void addResourceEvent(Dictionary options)
    {
        GAResourceFlowType flowType = GAResourceFlowType.Undefined;
        String currency = "";
        float amount = 0;
        String itemType = "";
        String itemId = "";
        String fields = "";
        boolean mergeFields = false;

        if(options != null)
        {
            Set<String> keys = options.keySet();
            for(String key : keys)
            {
                if(key.equals("flowType"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        if(value.equals("Source"))
                        {
                            flowType = GAResourceFlowType.Source;
                        }
                        else if(value.equals("Sink"))
                        {
                            flowType = GAResourceFlowType.Sink;
                        }
                    }
                }
                else if(key.equals("currency"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        currency = value + "";
                    }
                }
                else if(key.equals("amount"))
                {
                    Object value = options.get(key);
                    if(value instanceof Number)
                    {
                        Number n = (Number)value;
                        amount = n.floatValue();
                    }
                    else
                    {
                        Log.d("GameAnalytics", "'" + key + "' value has the wrong type: " + value.getClass());
                    }
                }
                else if(key.equals("itemType"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        itemType = value + "";
                    }
                }
                else if(key.equals("itemId"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        itemId = value + "";
                    }
                }
                else if (key.equals("customFields"))
                {
                    Object value = options.get(key);
                    if (value != null)
                    {
                        fields = value + "";
                    }
                }
                else if (key.equals("mergeFields")) {
                    Object value = options.get(key);
                    if (value instanceof Boolean) {
                        Boolean b = (Boolean) value;
                        mergeFields = b.booleanValue();
                    }
                }
            }
        }
        if (!fields.isEmpty())
        {
            GameAnalytics.addResourceEvent(flowType.ordinal(), currency, amount, itemType, itemId, fields, mergeFields);
        }
        else
        {
            GameAnalytics.addResourceEvent(flowType.ordinal(), currency, amount, itemType, itemId);
        }
    }

    @UsedByGodot
    public void addProgressionEvent(Dictionary options)
    {
        GAProgressionStatus progressionStatus = GAProgressionStatus.Undefined;
        String progression01 = "";
        String progression02 = "";
        String progression03 = "";
        Double score = null;
        String fields = "";
        boolean mergeFields = false;

        if(options != null)
        {
            Set<String> keys = options.keySet();
            for(String key : keys)
            {
                if(key.equals("progressionStatus"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        if(value.equals("Start"))
                        {
                            progressionStatus = GAProgressionStatus.Start;
                        }
                        else if(value.equals("Complete"))
                        {
                            progressionStatus = GAProgressionStatus.Complete;
                        }
                        else if(value.equals("Fail"))
                        {
                            progressionStatus = GAProgressionStatus.Fail;
                        }
                    }
                }
                else if(key.equals("progression01"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        progression01 = value + "";
                    }
                }
                else if(key.equals("progression02"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        progression02 = value + "";
                    }
                }
                else if(key.equals("progression03"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        progression03 = value + "";
                    }
                }
                else if(key.equals("score"))
                {
                    Object value = options.get(key);
                    if(value instanceof Number)
                    {
                        Number n = (Number)value;
                        score = n.doubleValue();
                    }
                    else
                    {
                        Log.d("GameAnalytics", "'" + key + "' value has the wrong type: " + value.getClass());
                    }
                }
                else if (key.equals("customFields"))
                {
                    Object value = options.get(key);
                    if (value != null)
                    {
                        fields = value + "";
                    }
                }
                else if (key.equals("mergeFields")) {
                    Object value = options.get(key);
                    if (value instanceof Boolean) {
                        Boolean b = (Boolean) value;
                        mergeFields = b.booleanValue();
                    }
                }
            }
        }

        if(score != null)
        {
            if (!fields.isEmpty())
            {
                GameAnalytics.addProgressionEvent(progressionStatus.ordinal(), progression01, progression02, progression03, score.doubleValue(), fields, mergeFields);
            }
            else
            {
                GameAnalytics.addProgressionEvent(progressionStatus.ordinal(), progression01, progression02, progression03, score.doubleValue());
            }

        }
        else
        {
            if (!fields.isEmpty())
            {
                GameAnalytics.addProgressionEvent(progressionStatus.ordinal(), progression01, progression02, progression03, fields, mergeFields);
            }
            else
            {
                GameAnalytics.addProgressionEvent(progressionStatus.ordinal(), progression01, progression02, progression03);
            }
        }
    }

    @UsedByGodot
    public void addDesignEvent(Dictionary options)
    {
        String eventId = "";
        Double value = null;
        String fields = "";
        boolean mergeFields = false;

        if(options != null)
        {
            Set<String> keys = options.keySet();
            for(String key : keys)
            {
                if(key.equals("eventId"))
                {
                    Object v = options.get(key);
                    if(v != null)
                    {
                        eventId = v + "";
                    }
                }
                else if(key.equals("value"))
                {
                    Object v = options.get(key);
                    if(v instanceof Number)
                    {
                        Number n = (Number)v;
                        value = n.doubleValue();
                    }
                    else
                    {
                        Log.d("GameAnalytics", "'" + key + "' value has the wrong type: " + value.getClass());
                    }
                }
                else if (key.equals("customFields"))
                {
                    Object v = options.get(key);
                    if (v != null)
                    {
                        fields = v + "";
                    }
                }
                else if (key.equals("mergeFields")) {
                    Object v = options.get(key);
                    if (v instanceof Boolean) {
                        Boolean b = (Boolean) v;
                        mergeFields = b.booleanValue();
                    }
                }
            }
        }

        if(value != null)
        {
            if (!fields.isEmpty())
            {
                GameAnalytics.addDesignEvent(eventId, value.doubleValue(), fields, mergeFields);
            }
            else
            {
                GameAnalytics.addDesignEvent(eventId, value.doubleValue());
            }
        }
        else
        {
            if (!fields.isEmpty())
            {
                GameAnalytics.addDesignEvent(eventId, fields, mergeFields);
            }
            else
            {
                GameAnalytics.addDesignEvent(eventId);
            }
        }
    }

    @UsedByGodot
    public void addErrorEvent(Dictionary options)
    {
        GAErrorSeverity severity = GAErrorSeverity.Undefined;
        String message = "";
        String fields = "";
        boolean mergeFields = false;

        if(options != null)
        {
            Set<String> keys = options.keySet();
            for(String key : keys)
            {
                if(key.equals("severity"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        if(value.equals("Debug"))
                        {
                            severity = GAErrorSeverity.Debug;
                        }
                        else if(value.equals("Info"))
                        {
                            severity = GAErrorSeverity.Info;
                        }
                        else if(value.equals("Warning"))
                        {
                            severity = GAErrorSeverity.Warning;
                        }
                        else if(value.equals("Error"))
                        {
                            severity = GAErrorSeverity.Error;
                        }
                        else if(value.equals("Critical"))
                        {
                            severity = GAErrorSeverity.Critical;
                        }
                    }
                }
                else if(key.equals("message"))
                {
                    Object value = options.get(key);
                    if(value != null)
                    {
                        message = value + "";
                    }
                }
                else if (key.equals("customFields"))
                {
                    Object value = options.get(key);
                    if (value != null)
                    {
                        fields = value + "";
                    }
                }
                else if (key.equals("mergeFields")) {
                    Object value = options.get(key);
                    if (value instanceof Boolean) {
                        Boolean b = (Boolean) value;
                        mergeFields = b.booleanValue();
                    }
                }
            }
        }
        if (!fields.isEmpty())
        {
            GameAnalytics.addErrorEvent(severity.ordinal(), message, fields, mergeFields);
        }
        else
        {
            GameAnalytics.addErrorEvent(severity.ordinal(), message);
        }
    }

    @UsedByGodot
    public void addAdEvent(Dictionary options) {
        GAAdAction adAction = GAAdAction.Undefined;
        GAAdType adType = GAAdType.Undefined;
        GAAdError noAdReason = GAAdError.Undefined;
        String adSdkName = "";
        String adPlacement = "";
        boolean sendDuration = false;
        Long duration = null;
        String fields = "";
        boolean mergeFields = false;

        if (options != null) {
            Set<String> keys = options.keySet();
            for (String key : keys) {
                if (key.equals("adAction")) {
                    Object value = options.get(key);
                    if (value != null) {
                        if (value.equals("Clicked")) {
                            adAction = GAAdAction.Clicked;
                        }
                        else if (value.equals("Show")) {
                            adAction = GAAdAction.Show;
                        }
                        else if (value.equals("FailedShow")) {
                            adAction = GAAdAction.FailedShow;
                        }
                        else if (value.equals("RewardReceived")) {
                            adAction = GAAdAction.RewardReceived;
                        }
                    }
                }
                else if (key.equals("adType")) {
                    Object value = options.get(key);
                    if (value != null) {
                        if (value.equals("Video")) {
                            adType = GAAdType.Video;
                        }
                        else if (value.equals("RewardedVideo")) {
                            adType = GAAdType.RewardedVideo;
                        }
                        else if (value.equals("Playable")) {
                            adType = GAAdType.Playable;
                        }
                        else if (value.equals("Interstitial")) {
                            adType = GAAdType.Interstitial;
                        }
                        else if (value.equals("OfferWall")) {
                            adType = GAAdType.OfferWall;
                        }
                        else if (value.equals("Banner")) {
                            adType = GAAdType.Banner;
                        }
                    }
                }
                else if (key.equals("adSdkName")) {
                    Object value = options.get(key);
                    if (value != null) {
                        adSdkName = value + "";
                    }
                }
                else if (key.equals("adPlacement")) {
                    Object value = options.get(key);
                    if (value != null) {
                        adPlacement = value + "";
                    }
                }
                else if (key.equals("duration")) {
                    Object v = options.get(key);
                    if (v instanceof Number) {
                        Number n = (Number) v;
                        duration = n.longValue();
                        sendDuration = true;
                    } else {
                        Log.d("GameAnalytics", "'" + key + "' value has the wrong type: " + v.getClass());
                    }
                }
                else if (key.equals("noAdReason")) {
                    Object value = options.get(key);
                    if (value != null) {
                        if (value.equals("Unknown")) {
                            noAdReason = GAAdError.Unknown;
                        }
                        else if (value.equals("Offline")) {
                            noAdReason = GAAdError.Offline;
                        }
                        else if (value.equals("NoFill")) {
                            noAdReason = GAAdError.NoFill;
                        }
                        else if (value.equals("InternalError")) {
                            noAdReason = GAAdError.InternalError;
                        }
                        else if (value.equals("InvalidRequest")) {
                            noAdReason = GAAdError.InvalidRequest;
                        }
                        else if (value.equals("UnableToPrecache")) {
                            noAdReason = GAAdError.UnableToPrecache;
                        }
                    }
                }
                else if (key.equals("customFields")) {
                    Object value = options.get(key);
                    if (value != null) {
                        fields = value + "";
                    }
                }
                else if (key.equals("mergeFields")) {
                    Object value = options.get(key);
                    if (value instanceof Boolean) {
                        Boolean b = (Boolean) value;
                        mergeFields = b.booleanValue();
                    }
                }
            }
        }

        if(adPlacement.isEmpty()){
            adPlacement = "undefined";
        }

        if(sendDuration)
        {
            if (!fields.isEmpty())
            {
                GameAnalytics.addAdEvent(adAction.ordinal(), adType.ordinal(), adSdkName, adPlacement, duration, fields, mergeFields);
            }
            else
            {
                GameAnalytics.addAdEvent(adAction.ordinal(), adType.ordinal(), adSdkName, adPlacement, duration);
            }
        }
        else
        {
            if (!fields.isEmpty())
            {
                GameAnalytics.addAdEvent(adAction.ordinal(), adType.ordinal(), adSdkName, adPlacement,noAdReason.ordinal(), fields, mergeFields);
            }
            else
            {
                GameAnalytics.addAdEvent(adAction.ordinal(), adType.ordinal(), adSdkName, adPlacement,noAdReason.ordinal());
            }
        }
    }

    @UsedByGodot
    public void addImpressionEvent(String networkName, String networkVersion, Dictionary options) {
        JSONObject jsonObject = new JSONObject();
        if (options != null) {
            Set<String> keys = options.keySet();
            for (String key : keys) {
                try {
                    jsonObject.put(key, options.get(key));
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            return;
        }
        GameAnalytics.addImpressionEvent(networkName, networkVersion, jsonObject);
    }

    @UsedByGodot
    public void setEnabledInfoLog(boolean flag)
    {
        GameAnalytics.setEnabledInfoLog(flag);
    }

    @UsedByGodot
    public void setEnabledVerboseLog(boolean flag)
    {
        GameAnalytics.setEnabledVerboseLog(flag);
    }

    @UsedByGodot
    public void setEnabledManualSessionHandling(boolean flag)
    {
        GameAnalytics.setEnabledManualSessionHandling(flag);
    }

    @UsedByGodot
    public void setEnabledErrorReporting(boolean flag)
    {
        GameAnalytics.setEnabledErrorReporting(flag);
    }

    @UsedByGodot
    public void setEnabledEventSubmission(boolean flag)
    {
        GameAnalytics.setEnabledEventSubmission(flag);
    }

    @UsedByGodot
    public void setCustomDimension01(String dimension)
    {
        GameAnalytics.setCustomDimension01(dimension);
    }

    @UsedByGodot
    public void setCustomDimension02(String dimension)
    {
        GameAnalytics.setCustomDimension02(dimension);
    }

    @UsedByGodot
    public void setCustomDimension03(String dimension)
    {
        GameAnalytics.setCustomDimension03(dimension);
    }

    @UsedByGodot
    public void setGlobalCustomEventFields(String customFields)
    {
        GameAnalytics.setGlobalCustomEventFields(customFields);
    }

    @UsedByGodot
    public String getRemoteConfigsValueAsString(Dictionary options)
    {
        String key = "";
        String defaultValue = null;

        if(options != null)
        {
            Set<String> keys = options.keySet();
            for(String k : keys)
            {
                if(k.equals("key"))
                {
                    Object value = options.get(k);
                    if(value != null)
                    {
                        key = value + "";
                    }
                }
                else if(k.equals("defaultValue"))
                {
                    Object value = options.get(k);
                    if(value != null)
                    {
                        defaultValue = value + "";
                    }
                }
            }
        }

        if(defaultValue != null)
        {
            return GameAnalytics.getRemoteConfigsValueAsString(key, defaultValue);
        }
        else
        {
            return GameAnalytics.getRemoteConfigsValueAsString(key);
        }
    }

    @UsedByGodot
    public boolean isRemoteConfigsReady()
    {
        return GameAnalytics.isRemoteConfigsReady();
    }

    @UsedByGodot
    public String getRemoteConfigsContentAsString()
    {
        return GameAnalytics.getRemoteConfigsContentAsString();
    }

    @UsedByGodot
    public void startSession()
    {
        GameAnalytics.startSession();
    }

    @UsedByGodot
    public void endSession()
    {
        GameAnalytics.endSession();
    }
}
